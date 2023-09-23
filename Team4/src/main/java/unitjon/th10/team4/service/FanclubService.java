package unitjon.th10.team4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import unitjon.th10.team4.dto.req.FanclubSaveDto;
import unitjon.th10.team4.entity.Fanclub;
import unitjon.th10.team4.repository.FanclubRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class FanclubService {

    private final StringRedisTemplate redisTemplate;
    private final FanclubRepository fanclubRepository;
    private final S3Service s3service;

    public List<Fanclub> findFanclubRanking() {
        Set<String> fanclubSet = redisTemplate.opsForZSet().reverseRange("fanclub:ranking", 0, -1);
        List<String> fanclubIds = fanclubSet != null ? fanclubSet.stream().toList() : List.of();
        Iterable<Fanclub> fanclubs = fanclubRepository.findAllById(fanclubIds);
        return StreamSupport.stream(fanclubs.spliterator(), false).toList();
    }

    public Fanclub findFanclub(String id) {
        return fanclubRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 id입니다."));
    }

    public void saveFanclub(FanclubSaveDto fanclubSaveDto) {
        String logoImageUrl = s3service.saveImage(fanclubSaveDto.getLogoImage());
        String artistImageUrl = s3service.saveImage(fanclubSaveDto.getArtistImage());

        Fanclub fanclub = new Fanclub(
                fanclubSaveDto.getName(),
                logoImageUrl,
                fanclubSaveDto.getFanclubInfo(),
                fanclubSaveDto.getArtistName(),
                artistImageUrl
        );

        fanclub.setPoint(fanclubSaveDto.getPoint());

        fanclubRepository.save(fanclub);

        // 랭킹 보드에 추가
        redisTemplate.opsForZSet().add("fanclub:ranking", fanclub.getFanclubId(), fanclub.getPoint());
    }


    public void deleteFanclub(String id) {
        Fanclub fanclub = fanclubRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 id입니다."));
        fanclubRepository.delete(fanclub);
    }

    public void updatePoint(String id, int point) {
        Fanclub fanclub = fanclubRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 id입니다."));
        fanclub.setPoint(fanclub.getPoint() + point);
        redisTemplate.opsForZSet().incrementScore("fanclub:ranking", id, point);
        fanclubRepository.save(fanclub);
    }
}
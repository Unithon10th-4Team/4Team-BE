package unitjon.th10.team4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import unitjon.th10.team4.dto.req.FanclubSaveDto;
import unitjon.th10.team4.entity.Fanclub;
import unitjon.th10.team4.repository.FanclubRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class FanclubService {

    private final StringRedisTemplate redisTemplate;
    private final FanclubRepository fanclubRepository;
//    private final S3service s3service;

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
        // 이미지 저장 TODO
//        fanclub.setLogoUrl(s3service.saveImage(fanclubSaveDto.getLogo()));
//        fanclub.setArtistUrl(s3service.saveImage(fanclubSaveDto.getArtistImage()));
        String logoImageUrl = "https://test.com";
        String artistImageUrl = "https://test.com";

        Fanclub fanclub = new Fanclub(
                fanclubSaveDto.getName(),
                logoImageUrl,
                fanclubSaveDto.getFanclubInfo(),
                fanclubSaveDto.getArtistName(),
                artistImageUrl
        );

        fanclub.setPoint(fanclubSaveDto.getPoint());

        fanclubRepository.save(fanclub);
    }


    public void deleteFanclub(String id) {
        Fanclub fanclub = fanclubRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 id입니다."));
        fanclubRepository.delete(fanclub);
    }
}
package unitjon.th10.team4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import unitjon.th10.team4.dto.req.FanclubSaveDto;
import unitjon.th10.team4.entity.Fanclub;
import unitjon.th10.team4.repository.FanclubRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FanclubService {

    private final FanclubRepository fanclubRepository;
    private final S3Service s3service;

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
    }


    public void deleteFanclub(String id) {
        Fanclub fanclub = fanclubRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 id입니다."));
        fanclubRepository.delete(fanclub);
    }
}
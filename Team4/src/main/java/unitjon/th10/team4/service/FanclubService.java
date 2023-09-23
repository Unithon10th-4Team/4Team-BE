package unitjon.th10.team4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unitjon.th10.team4.dto.res.FanclubFindDto;
import unitjon.th10.team4.dto.req.FanclubSaveDto;
import unitjon.th10.team4.entity.Fanclub;
import unitjon.th10.team4.repository.FanclubRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FanclubService {
    private final FanclubRepository fanclubRepository;
    private final S3service s3service;
    public FanclubFindDto findFanclub(String id){
        Fanclub fanclub = fanclubRepository.findById(id).orElseThrow(()-> new RuntimeException("존재하지 않는 id입니다."));
        FanclubFindDto fanclubFindDto = FanclubFindDto.toDto(fanclub);
        return fanclubFindDto;


    }


    public void saveFanclub(FanclubSaveDto fanclubSaveDto){
        Fanclub fanclub = new Fanclub();
        fanclub.setFanclubId(UUID.randomUUID().toString());
        fanclub.setName(fanclubSaveDto.getName());
        fanclub.setFanclubInfo(fanclubSaveDto.getFanclub_info());
        fanclub.setLogoUrl(s3service.saveImage(fanclubSaveDto.getLogo()));
        fanclub.setArtist(fanclubSaveDto.getArtist());
        fanclub.setArtistUrl(s3service.saveImage(fanclubSaveDto.getArtist_image()));
        fanclub.setPoint(fanclub.getPoint());

        fanclubRepository.save(fanclub);
    }


    public void deleteFanclub(String id){
        fanclubRepository.deleteById(id);
    }
}
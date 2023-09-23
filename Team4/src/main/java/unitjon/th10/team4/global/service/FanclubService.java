package unitjon.th10.team4.global.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unitjon.th10.team4.global.dto.FanclubFindDto;
import unitjon.th10.team4.global.dto.FanclubSaveDto;
import unitjon.th10.team4.global.dto.FanclubUpdateDto;
import unitjon.th10.team4.global.entity.Fanclub;
import unitjon.th10.team4.global.repository.FanclubRepository;

import java.util.ArrayList;
import java.util.List;
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
        fanclub.setFanclub_id(UUID.randomUUID().toString());
        fanclub.setName(fanclubSaveDto.getName());
        fanclub.setFanclub_info(fanclubSaveDto.getFanclub_info());
        fanclub.setLogo_url(s3service.saveImage(fanclubSaveDto.getLogo()));
        fanclub.setArtist(fanclubSaveDto.getArtist());
        fanclub.setArtist_url(s3service.saveImage(fanclubSaveDto.getArtist_image()));
        fanclub.setPoint(fanclub.getPoint());

        fanclubRepository.save(fanclub);
    }


    public void updateFanclub(String id, FanclubUpdateDto fanclubUpdateDto) {
        Fanclub fanclub = fanclubRepository.findById(id).get(); //수정 필요
        if (fanclubUpdateDto.getName() != null) {
            fanclub.setName(fanclubUpdateDto.getName());
        }
        if (fanclubUpdateDto.getLogo() != null) {
            fanclub.setLogo_url(fanclubUpdateDto.getName());
        }
        if (fanclubUpdateDto.getFanclub_info() != null) {
            fanclub.setFanclub_info(fanclub.getFanclub_info());
        }
        if (fanclubUpdateDto.getArtist() != null) {
            fanclub.setArtist(fanclub.getArtist());
        }

        if (fanclubUpdateDto.getArtist_image() != null) {
            fanclub.setArtist_url(fanclub.getArtist_url());
        }

        if (fanclubUpdateDto.getPoint() != null) {
            fanclub.setPoint(fanclub.getPoint());
        }

    }


    public void deleteFanclub(String id){
        fanclubRepository.delete(id);

    }
}
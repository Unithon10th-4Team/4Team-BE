package unitjon.th10.team4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unitjon.th10.team4.dto.res.FanclubFindDto;
import unitjon.th10.team4.dto.req.FanclubSaveDto;
import unitjon.th10.team4.entity.Fanclub;
import unitjon.th10.team4.service.FanclubService;


@RequiredArgsConstructor
@RestController
public class FanclubController {

    private final FanclubService fanclubService;

    @GetMapping("/fanclubs/{id}")
    public FanclubFindDto findFanclub(@PathVariable("id") String id) {
        Fanclub fanclub = fanclubService.findFanclub(id);
        return FanclubFindDto.toDto(fanclub);
    }

//    @GetMapping("/fanclubs")
//    public List<FanclubFindDto> findFanclubRanking(){
//
//        return FanclubUpdateDtos;
//    }

    @PostMapping("/fanclubs")
    public void saveFanclub(@ModelAttribute FanclubSaveDto fanclubSaveDto){
        fanclubService.saveFanclub(fanclubSaveDto);
    }

    @DeleteMapping("/fanclubs/{id}")
    public void deleteFanclub(@PathVariable("id") String id){
        fanclubService.deleteFanclub(id);
    }
}

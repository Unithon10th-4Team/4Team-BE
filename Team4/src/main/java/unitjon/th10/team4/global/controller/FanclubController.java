package unitjon.th10.team4.global.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unitjon.th10.team4.global.dto.FanclubFindDto;
import unitjon.th10.team4.global.dto.FanclubSaveDto;
import unitjon.th10.team4.global.dto.FanclubUpdateDto;
import unitjon.th10.team4.global.service.FanclubService;


@RequiredArgsConstructor
@RestController
public class FanclubController {
    private final FanclubService fanclubService;


    /**
     * 팬클럽 조회
     */
    @GetMapping("/fanclubs/{fanclubId}")
    public FanclubFindDto findFanclub(@PathVariable("fanclubId") String id) {
        FanclubFindDto fanclubFindDto = fanclubService.findFanclub(id);
        return fanclubFindDto;
    }

    /**
     * 팬클럽 랭킹 목록 조회
     */
//    @GetMapping("/fanclubs")
//    public List<FanclubFindDto> findFanclubRanking(){
//
//        return FanclubUpdateDtos;
//    }

    /**
     * 팬클럽 추가
     */
    @PostMapping("/fanclubs")
    public void saveFanclub(@ModelAttribute FanclubSaveDto fanclubSaveDto){
        fanclubService.saveFanclub(fanclubSaveDto);

    }

    /**
     * 팬클럽 수정
     */
    @PatchMapping("/fanclubs/{fanclubId}")
    public void updateFanclub(@PathVariable String id, @ModelAttribute FanclubUpdateDto fanclubUpdateDto){
        fanclubService.updateFanclub(id, fanclubUpdateDto);

    }


    /**
     * 팬클럽 삭제
     */
    @DeleteMapping("/fanclubs/{fanclubId}")
    public void deleteFanclub(@PathVariable("fanclubId") String id){
        fanclubService.deleteFanclub(id);
    }



}

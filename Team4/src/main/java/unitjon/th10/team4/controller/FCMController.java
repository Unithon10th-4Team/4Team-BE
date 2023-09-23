package unitjon.th10.team4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unitjon.th10.team4.dto.req.FCMReqDTO;
import unitjon.th10.team4.service.FcmService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/fcm")
public class FCMController {
    private final FcmService fcmService;

    @PostMapping("/messaging")
    public void testFcm(@RequestBody FCMReqDTO.messaging messagingDTO) throws IOException {
        fcmService.sendMessageTo(messagingDTO.getTargetToken(), messagingDTO.getTitle(), messagingDTO.getBody());
    }
}

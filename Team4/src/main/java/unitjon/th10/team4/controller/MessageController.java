package unitjon.th10.team4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unitjon.th10.team4.dto.req.MessageReqDTO;
import unitjon.th10.team4.service.MessageService;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/v1/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public void sendToEmoji(@RequestBody MessageReqDTO.Emoji emojiDTO){
        messageService.messageToEmoji(emojiDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getMessageReceivedList(@RequestParam("memberName")String memberName){
//        return new ResponseEntity<>(messageService.getMessageLogList(memberName), HttpStatus.OK);
        return messageService.getMessageLogList(memberName);
    }
}

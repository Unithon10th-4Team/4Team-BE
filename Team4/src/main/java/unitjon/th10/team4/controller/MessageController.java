package unitjon.th10.team4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unitjon.th10.team4.dto.req.MessageReqDTO;
import unitjon.th10.team4.dto.res.MessageResDTO;
import unitjon.th10.team4.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("")
    public void sendToEmoji(@RequestBody MessageReqDTO.Emoji emojiDTO) {
        messageService.messageToEmoji(emojiDTO);
    }

    @GetMapping("")
    public List<MessageResDTO.List> getMessageReceivedList(@RequestParam("memberName") String memberName) {
        return messageService.getMessageLogList(memberName);
    }
}

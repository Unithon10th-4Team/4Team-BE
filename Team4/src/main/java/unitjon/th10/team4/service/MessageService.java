package unitjon.th10.team4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import unitjon.th10.team4.config.SseEmitters;
import unitjon.th10.team4.dto.event.EmojiEvent;
import unitjon.th10.team4.dto.event.StatusUpdatedEvent;
import unitjon.th10.team4.dto.req.MessageReqDTO;
import unitjon.th10.team4.dto.res.MessageResDTO;
import unitjon.th10.team4.entity.Message;
import unitjon.th10.team4.repository.MessageRepository;

import java.io.IOException;
import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class MessageService {

    private final MessageRepository messageRepository;
    private final FcmService fcmService;
    private final RedisTemplate<String,List<String>> redisTemplate;
    private final SseEmitters sseEmitters;
    private final MemberService memberService;
    private final FanclubService fanclubService;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public void messageToEmoji(MessageReqDTO.Emoji emojiDTO){
        if (sseEmitters.existMemberInSession(emojiDTO.getTo())) {
            throw new RuntimeException("회원 SSE 커넥션 정보 없음");
        }
        String messageId = UUID.randomUUID().toString();
        String sender = emojiDTO.getFrom();

        Message message = messageRepository.save(
                Message.builder()
                        .message_id(messageId)
                        .contents(emojiDTO.getContents())
                        .from(emojiDTO.getFrom())
                        .to(emojiDTO.getTo())
                        .timeStamp(emojiDTO.getTimeStamp())
                        .build());
        memberService.updatePoint(sender,5);
        fanclubService.updatePoint(memberService.getFanclubIdByName(sender),5);
        setMessageListOnMessageLog(emojiDTO,messageId);
        publisher.publishEvent(new EmojiEvent(message));
    }

    @Transactional
    public ResponseEntity<?> getMessageLogList(final String memberName) {
        String messageListKeyValue = "member:" + memberName + ":messages";
        ValueOperations<String, List<String>> valueOperations = redisTemplate.opsForValue();
        List<String> messageLog = valueOperations.get(messageListKeyValue);
        List<MessageResDTO.List> messageReceivedList = getMessageReceivedList(messageLog);
        Collections.reverse(messageReceivedList);
        return new ResponseEntity<>(messageReceivedList, HttpStatus.OK);
    }

    private void setMessageListOnMessageLog(MessageReqDTO.Emoji emojiDTO,String messageId){
        ValueOperations<String, List<String>> test = redisTemplate.opsForValue();
        String messageListKeyValue = "member:" + emojiDTO.getTo() + ":messages";
        List<String> originMessageLog = test.get(messageListKeyValue);
        if (originMessageLog==null) originMessageLog=new ArrayList<>();
        originMessageLog.add(messageId);
        test.set(messageListKeyValue,originMessageLog);
    }

    private List<MessageResDTO.List> getMessageReceivedList(List<String> messageLog){
        List<MessageResDTO.List> receivedList=new ArrayList<>();
        for (String id:messageLog){
            Message message = messageRepository.findById(id).get();
            receivedList.add(
                    MessageResDTO.List.builder()
                            .sender(message.getFrom())
                            .contents(message.getContents())
                            .timeStamp(message.getTimeStamp())
                    .build());
        }
        return receivedList;
    }

    @EventListener
    private void notificationUseSseToReceiver(EmojiEvent event) throws IOException {
        log.info("Notification EventListener");
        Message message = event.message();
        SseEmitter findSse = sseEmitters.get(message.getTo());
        findSse.send(SseEmitter.event()
                        .data(MessageResDTO.Emoji
                                .builder()
                                .sender(message.getFrom())
                                .contents(message.getContents())
                                .build())
        );
    }

}

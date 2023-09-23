package unitjon.th10.team4.service;

import com.google.api.client.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import unitjon.th10.team4.config.SseEmitters;
import unitjon.th10.team4.dto.req.MessageReqDTO;
import unitjon.th10.team4.dto.res.MessageResDTO;
import unitjon.th10.team4.entity.Message;
import unitjon.th10.team4.repository.MessageRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final FcmService fcmService;
    private final RedisTemplate<String,List<String>> redisTemplate;
    private final SseEmitters sseEmitters;
    private final MemberService memberService;
    private final FanclubService fanclubService;

    @Transactional
    public void messageToEmoji(MessageReqDTO.Emoji emojiDTO){
        if (sseEmitters.existMemberInSession(emojiDTO.getTo())) {
            throw new RuntimeException("회원 SSE 커넥션 정보 없음");
        }
        String messageId = UUID.randomUUID().toString();
        String sender = emojiDTO.getFrom();

        messageRepository.save(
                Message.builder()
                        .message_id(messageId)
                        .contents(emojiDTO.getContents())
                        .from(emojiDTO.getFrom())
                        .to(emojiDTO.getTo())
                        .timeStamp(emojiDTO.getTimeStamp())
                .build());
        memberService.updatePoint(sender,5);
        fanclubService.updatePoint(memberService.getFanclubIdByName(sender),5);
        setMessageLogAndReceiverNotification(emojiDTO,messageId);
        //TODO : 발신자, 그룹 포인트 갱신
    }

    @Transactional
    public ResponseEntity<?> getMessageLogList(final String memberName) {
        String messageListKeyValue = "member:" + memberName + ":messages";
        ValueOperations<String, List<String>> valueOperations = redisTemplate.opsForValue();
        List<String> messageLog = valueOperations.get(messageListKeyValue);
        List<MessageResDTO.List> messageReceivedList = getMessageReceivedList(messageLog);
        return new ResponseEntity<>(messageReceivedList, HttpStatus.OK);
    }

    private void setMessageLogAndReceiverNotification(MessageReqDTO.Emoji emojiDTO,String messageId){
        setMessageListOnMessageLog(emojiDTO,messageId);
        notificationUseSseToReceiver(emojiDTO);
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

    @SneakyThrows
    private void notificationUseSseToReceiver(MessageReqDTO.Emoji emojiDTO) {
        String eventMessage = encodeUTF8(emojiDTO.getFrom() + "님이 " + emojiDTO.getContents() + "를 보냈어요");
        System.out.println(eventMessage);
        SseEmitter findSse = sseEmitters.get(emojiDTO.getTo());
        findSse.send(SseEmitter.event()
                        .data(eventMessage)
        );
    }

    private String encodeUTF8(String originMessage){
        byte[] bytes = StringUtils.getBytesUtf8(originMessage);
        return StringUtils.newStringUtf8(bytes);
    }

}

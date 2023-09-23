package unitjon.th10.team4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void messageToEmoji(MessageReqDTO.Emoji emojiDTO){
        String messageId = UUID.randomUUID().toString();
        messageRepository.save(
                Message.builder()
                        .message_id(messageId)
                        .contents(emojiDTO.getContents())
                        .from(emojiDTO.getFrom())
                        .to(emojiDTO.getTo())
                        .timeStamp(emojiDTO.getTimeStamp())
                .build());

        setMessageListOnMessageLog(emojiDTO,messageId);

        //TODO : FCM 전달
        //TODO : 발신자, 그룹 포인트 갱신
        //        fcmService.sendMessageTo(emojiDTO.getFcmToken(),emojiDTO.getFrom()+"님이"+emojiDTO.getContents()+"를 보냈어요","");
    }

    @Transactional
    public ResponseEntity<?> getMessageLogList(final String memberName) {
        String messageListKeyValue = "member:" + memberName + ":messages";
        ValueOperations<String, List<String>> valueOperations = redisTemplate.opsForValue();
        List<String> messageLog = valueOperations.get(messageListKeyValue);
        List<MessageResDTO.List> messageReceivedList = getMessageReceivedList(messageLog);
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
}

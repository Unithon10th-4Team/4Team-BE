package unitjon.th10.team4.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Builder
@RedisHash("message")
public class Message implements Serializable {
    @Id
    private String message_id;
    private String contents;
    private String from;
    private String to;
    private String position;
    private String timeStamp;
}

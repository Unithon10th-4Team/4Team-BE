package unitjon.th10.team4.dto.res;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import unitjon.th10.team4.entity.SseType;

@Getter
public class MessageResDTO {

    @Builder
    @Getter
    public static class List{
        private String sender;
        private String contents;
        private String timeStamp;

        @Override
        public String toString() {
            return "List [sender=" + sender + ", contents=" + contents +", timeStamp="+timeStamp+ "]";
        }
    }

    @Getter
    @Builder
    public static class Emoji{
        private String sender;
        private String contents;
        private SseType sseType;
    }
}

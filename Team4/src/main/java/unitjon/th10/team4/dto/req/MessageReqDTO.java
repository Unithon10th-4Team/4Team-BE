package unitjon.th10.team4.dto.req;

import lombok.Getter;

@Getter
public class MessageReqDTO {

    @Getter
    public static class Emoji {
        private String fcmToken;
        private String contents;
        private String from;
        private String to;
    }

    @Getter
    public static class List{
        private String name;
    }
}

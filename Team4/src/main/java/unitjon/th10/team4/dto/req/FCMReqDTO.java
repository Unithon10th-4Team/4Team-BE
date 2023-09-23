package unitjon.th10.team4.dto.req;

import lombok.Getter;

@Getter
public class FCMReqDTO {

    @Getter
    public static class messaging{
        private String targetToken;
        private String title;
        private String body;
    }
}

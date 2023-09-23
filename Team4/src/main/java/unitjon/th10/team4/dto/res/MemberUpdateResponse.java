package unitjon.th10.team4.dto.res;

import lombok.*;

@Getter
public class MemberUpdateResponse {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Status{
        private String name;
        private boolean isOnline;
    }
}

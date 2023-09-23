package unitjon.th10.team4.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdateResponse {

    @Getter
    @Builder
    public static class Status{
        private String name;
        private Boolean isOnline;
    }
}

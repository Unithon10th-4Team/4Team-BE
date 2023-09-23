package unitjon.th10.team4.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private String name;
    private String fanclubId;
    private String profileImageUrl;
    private int point;
}

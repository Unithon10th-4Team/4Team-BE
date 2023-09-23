package unitjon.th10.team4.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetNearMembersRequest {

    private double latitude;
    private double longitude;
    private int distance;
}

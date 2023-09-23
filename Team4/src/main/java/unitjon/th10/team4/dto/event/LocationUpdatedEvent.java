package unitjon.th10.team4.dto.event;

import org.springframework.data.geo.Point;
import unitjon.th10.team4.entity.Member;

public record LocationUpdatedEvent(Member member) {

}

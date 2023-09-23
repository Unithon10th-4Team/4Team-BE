package unitjon.th10.team4.dto.event;

import unitjon.th10.team4.entity.Member;

public record StatusUpdatedEvent(Member member) {
}

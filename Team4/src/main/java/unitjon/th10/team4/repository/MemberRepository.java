package unitjon.th10.team4.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;
import unitjon.th10.team4.entity.Member;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {
    List<Member> findByLocationNear(Point point, Distance distance);
}

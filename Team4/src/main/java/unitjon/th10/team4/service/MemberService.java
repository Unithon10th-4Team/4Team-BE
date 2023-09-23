package unitjon.th10.team4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import unitjon.th10.team4.entity.Member;
import unitjon.th10.team4.repository.MemberRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final RedisTemplate<String, Member> redisTemplate;
    private final MemberRepository memberRepository;

    public Member getMember(String name) {
        return memberRepository.findById(name).orElseThrow(() -> new RuntimeException("존재하지 않는 이름입니다."));
    }

    public void addMember(
            String name,
            String fanclubId,
            String fcmToken,
            Point location,
            MultipartFile profileImage
    ) {
        // 이미지 업로드 TODO
        String profileUrl = "";

        // 이름 중복 확인
        if (memberRepository.existsById(name)) {
            throw new RuntimeException("이미 존재하는 이름입니다.");
        }

        // 멤버 생성 및 저장
        memberRepository.save(new Member(name, fanclubId, profileUrl, location, fcmToken));
    }


    public List<Member> getNearMembers(String name, Point coordinates, int distance) {
        Member member = memberRepository.findById(name).orElseThrow(() -> new RuntimeException("존재하지 않는 이름입니다."));
        List<Member> nearMembers = memberRepository.findByLocationNear(coordinates, new Distance(distance, RedisGeoCommands.DistanceUnit.METERS));

        // 본인 제외 및 오프라인 제외
        nearMembers.removeIf(e -> e.getName().equals(member.getName()) || !e.isOnline());

        return nearMembers;
    }

    public void updateLocation(String name, Point point) {
        Member member = memberRepository.findById(name).orElseThrow(() -> new RuntimeException("존재하지 않는 이름입니다."));
        member.setLocation(point);
        memberRepository.save(member);
    }

    public void updateStatus(String name, boolean isOnline) {
        log.info("updateStatus: " + name + ", " + isOnline);
        Member member = memberRepository.findById(name).orElseThrow(() -> new RuntimeException("존재하지 않는 이름입니다."));
        member.setOnline(isOnline);
        memberRepository.save(member);
    }

    public void updatePoint(String name,int point){
        log.info("{} new get point :{}",name,point);
        Member member = memberRepository.findById(name).orElseThrow(() -> new RuntimeException("존재하지 않는 이름이다."));
        member.setPoint(member.getPoint()+point);
        memberRepository.save(member);
    }
}

package unitjon.th10.team4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;
import unitjon.th10.team4.dto.req.CreateMemberRequest;
import unitjon.th10.team4.dto.req.GetNearMembersRequest;
import unitjon.th10.team4.dto.req.UpdateMemberLocationRequest;
import unitjon.th10.team4.dto.req.UpdateMemberStatusRequest;
import unitjon.th10.team4.dto.res.MemberListResponse;
import unitjon.th10.team4.dto.res.MemberResponse;
import unitjon.th10.team4.entity.Member;
import unitjon.th10.team4.service.MemberService;

import java.util.List;


@RequestMapping("members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("{name}")
    MemberResponse getMember(@PathVariable String name) {
        Member member = memberService.getMember(name);
        return new MemberResponse(
                member.getName(),
                member.getFanclubId(),
                member.getProfileImageUrl(),
                member.getPoint()
        );
    }

    @PostMapping("")
    void addMember(@ModelAttribute CreateMemberRequest request) {
        memberService.addMember(
                request.getName(),
                request.getFanclubId(),
                request.getFcmToken(),
                new Point(request.getLongitude(), request.getLatitude()),
                request.getProfileImage()
        );
    }

    @GetMapping("{name}/near")
    List<MemberListResponse> getNearMembers(@PathVariable String name, @RequestBody GetNearMembersRequest request) {
        List<Member> members = memberService.getNearMembers(
                name,
                new Point(request.getLongitude(), request.getLatitude()),
                request.getDistance()
        );
        return members.stream()
                .map(member -> new MemberListResponse(member.getName(), member.getProfileImageUrl()))
                .toList();
    }

    @PatchMapping("{name}/location")
    void updateLocation(@PathVariable String name, @RequestBody UpdateMemberLocationRequest request) {
        memberService.updateLocation(name, new Point(request.getLongitude(), request.getLatitude()));
    }

    @PatchMapping("{name}/status")
    void updateStatus(@PathVariable String name, @RequestBody UpdateMemberStatusRequest request) {
        memberService.updateStatus(name, request.isOnline());
    }
}

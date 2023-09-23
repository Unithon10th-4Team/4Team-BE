package unitjon.th10.team4.config.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import unitjon.th10.team4.entity.Fanclub;
import unitjon.th10.team4.entity.Member;
import org.springframework.data.geo.Point;
import unitjon.th10.team4.repository.FanclubRepository;
import unitjon.th10.team4.repository.MemberRepository;
import unitjon.th10.team4.service.FanclubService;
import unitjon.th10.team4.service.MemberService;

import java.awt.*;

@Component
@RequiredArgsConstructor
@Order(1)
public class DataInitialize implements ApplicationRunner {

    private final FanclubService fanclubService;
    private final MemberService memberService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Fanclub f1 = new Fanclub("이너써클", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%9C%84%EB%84%88+%ED%8C%AC%EB%8D%A4_%EC%9D%B4%EB%84%88%EC%8D%A8%ED%81%B4.png", "위너 팬클럽.\n" +
                "2014년에 1기가 창단되었습니다.", "위너", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%ED%8B%B0%EC%8A%A4%ED%8A%B8_%EC%9C%84%EB%84%88.png");
        Fanclub f2 = new Fanclub("마이", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%97%90%EC%8A%A4%ED%8C%8C+%ED%8C%AC%EB%8D%A4_%EB%A7%88%EC%9D%B4.png", "에스파 팬클럽.\n" +
                "2020년에 1기가 창단되었습니다.", "에스파", "https://github.com/Unithon10th-Team4/Team4-BE/assets/39437170/3b5e68f9-f2cb-49f4-bab3-7ac3194c5669");
        Fanclub f3 = new Fanclub("샤이니월드", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%83%A4%EC%9D%B4%EB%8B%88+%ED%8C%AC%EB%8D%A4_%EC%83%A4%EC%9D%B4%EB%8B%88%EC%9B%94%EB%93%9C.png", "샤이니 팬클럽.\n" +
                "2009년에 1기가 창단되었습니다.", "샤이니", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%ED%8B%B0%EC%8A%A4%ED%8A%B8_%EC%83%A4%EC%9D%B4%EB%8B%88.png");
        Fanclub f4 = new Fanclub("엑소엘", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%97%91%EC%86%8C+%ED%8C%AC%EB%8D%A4_%EC%97%91%EC%86%8C%EC%97%98.png", "엑소 공식 팬클럽.\n" +
                "2014년에 1기가 창단되었습니다.", "엑소", "https://github.com/Unithon10th-Team4/Team4-BE/assets/39437170/48ad4e42-d62c-4ba8-9106-12be9016f9fa");
        Fanclub f5 = new Fanclub("스테이", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%EC%9D%B4%EB%93%A4+%ED%8C%AC%EB%8D%A4_%EB%84%A4%EB%B2%84%EB%9E%9C%EB%93%9C.png", "스트레이키즈 팬클럽.\n" +
                "2017년에 1기가 창단되었습니다.", "스트레이키즈", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%ED%8B%B0%EC%8A%A4%ED%8A%B8_%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%9D%B4%ED%82%A4%EC%A6%88.png");
        Fanclub f6 = new Fanclub("유애나", "https://github.com/Unithon10th-Team4/Team4-BE/assets/39437170/c09faa0c-548c-4cce-9967-c4935b0dcfa2", "아이유 공식 팬클럽.\n" +
                "2017년에 1기가 창단되었습니다.", "아이유", "https://github.com/Unithon10th-Team4/Team4-BE/assets/39437170/45a592ad-5823-4c50-9f35-303c19635099");
        Fanclub f7 = new Fanclub("아미", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%A9%ED%83%84%EC%86%8C%EB%85%84%EB%8B%A8+%ED%8C%AC%EB%8D%A4_%EC%95%84%EB%AF%B8.png", "방탄소년단 공식 팬클럽.\n" +
                "2014년에 1기가 창단되었습니다.", "방탄소년단", "https://github.com/Unithon10th-Team4/Team4-BE/assets/39437170/ace6ed01-3570-4d0b-974c-be62dbbce6c4");
        Fanclub f8 = new Fanclub("피어나", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EB%A5%B4%EC%84%B8%EB%9D%BC%ED%95%8C+%ED%8C%AC%EB%8D%A4_%ED%94%BC%EC%96%B4%EB%82%98+.png", "르세라핌 팬클럽.\n" +
                "2023년에 1기가 창단되었습니다.", "르세라핌", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/LESSERAFIM.png");
        Fanclub f9 = new Fanclub("네버랜드", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%EC%9D%B4%EB%93%A4+%ED%8C%AC%EB%8D%A4_%EB%84%A4%EB%B2%84%EB%9E%9C%EB%93%9C.png", "아이들 팬클럽.\n" +
                "2018년에 1기가 창단되었습니다.", "아이들", "https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%ED%8B%B0%EC%8A%A4%ED%8A%B8_%EC%95%84%EC%9D%B4%EB%93%A4.png");
        Fanclub f10 = new Fanclub("몬베베", "https://media.discordapp.net/attachments/1153995173739634740/1155284977634779277/87b829ece672dbbb.png?width=450&height=450", "몬스타엑스 공식 팬클럽. \n" +
                "2016년에 1기가 창단되었습니다.", "몬스터엑스", "https://media.discordapp.net/attachments/1153995173739634740/1155285204706000996/bdc1bef3fcdd0629.png?width=1888&height=936");
        fanclubService.saveDummyFanclub(f1);
        fanclubService.saveDummyFanclub(f2);
        fanclubService.saveDummyFanclub(f3);
        fanclubService.saveDummyFanclub(f4);
        fanclubService.saveDummyFanclub(f5);
        fanclubService.saveDummyFanclub(f6);
        fanclubService.saveDummyFanclub(f7);
        fanclubService.saveDummyFanclub(f8);
        fanclubService.saveDummyFanclub(f9);
        fanclubService.saveDummyFanclub(f10);

        Member m1 = new Member("testuser1", f10.getFanclubId(), "https://github.com/Unithon10th-Team4/Team4-BE/assets/39437170/aba36e2d-0756-4434-8c21-948fdccbb580", memberDummyBasicLocation(), "test");
        Member m2 = new Member("testuser2", f3.getFanclubId(), "https://github.com/Unithon10th-Team4/Team4-BE/assets/39437170/aba36e2d-0756-4434-8c21-948fdccbb580", memberDummyBasicLocation(), "test");
        Member m3 = new Member("testuser3", f5.getFanclubId(), "https://github.com/Unithon10th-Team4/Team4-BE/assets/39437170/aba36e2d-0756-4434-8c21-948fdccbb580", memberDummyBasicLocation(), "test");
        memberService.addDummyMemberByBasic(m1);
        memberService.addDummyMemberByBasic(m2);
        memberService.addDummyMemberByBasic(m3);

    }

    private Point memberDummyBasicLocation(){
        return new Point(126.952641,37.545492);
    }
}

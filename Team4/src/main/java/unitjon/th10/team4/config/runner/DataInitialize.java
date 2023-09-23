package unitjon.th10.team4.config.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import unitjon.th10.team4.entity.Fanclub;
import unitjon.th10.team4.repository.FanclubRepository;
import unitjon.th10.team4.repository.MemberRepository;

@Component
@RequiredArgsConstructor
public class DataInitialize implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final FanclubRepository fanclubRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Fanclub("이너써클","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%9C%84%EB%84%88+%ED%8C%AC%EB%8D%A4_%EC%9D%B4%EB%84%88%EC%8D%A8%ED%81%B4.png","위너 팬클럽.\n" +
                "2014년에 1기가 창단되었습니다.","위너","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%ED%8B%B0%EC%8A%A4%ED%8A%B8_%EC%9C%84%EB%84%88.png");
        new Fanclub("마이","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%97%90%EC%8A%A4%ED%8C%8C+%ED%8C%AC%EB%8D%A4_%EB%A7%88%EC%9D%B4.png","에스파 팬클럽.\n" +
                "2020년에 1기가 창단되었습니다.","에스파","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%97%90%EC%8A%A4%ED%8C%8C+%ED%8C%AC%EB%8D%A4_%EB%A7%88%EC%9D%B4.png");
        new Fanclub("샤이니월드","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%83%A4%EC%9D%B4%EB%8B%88+%ED%8C%AC%EB%8D%A4_%EC%83%A4%EC%9D%B4%EB%8B%88%EC%9B%94%EB%93%9C.png","샤이니 팬클럽.\n" +
                "2009년에 1기가 창단되었습니다.","샤이니","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%ED%8B%B0%EC%8A%A4%ED%8A%B8_%EC%83%A4%EC%9D%B4%EB%8B%88.png");
        new Fanclub("엑소엘","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%97%91%EC%86%8C+%ED%8C%AC%EB%8D%A4_%EC%97%91%EC%86%8C%EC%97%98.png","엑소 공식 팬클럽.\n" +
                "2014년에 1기가 창단되었습니다.","엑소","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%ED%8B%B0%EC%8A%A4%ED%8A%B8_%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%9D%B4%ED%82%A4%EC%A6%88.png");
        new Fanclub("스테이","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%EC%9D%B4%EB%93%A4+%ED%8C%AC%EB%8D%A4_%EB%84%A4%EB%B2%84%EB%9E%9C%EB%93%9C.png","스트레이키즈 팬클럽.\n" +
                "2017년에 1기가 창단되었습니다.","스트레이키즈","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%ED%8B%B0%EC%8A%A4%ED%8A%B8_%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%9D%B4%ED%82%A4%EC%A6%88.png");
        new Fanclub("유애나","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%EC%9D%B4%EC%9C%A0+%ED%8C%AC%EB%8D%A4_%EC%9C%A0%EC%95%A0%EB%82%98.png","아이유 공식 팬클럽.\n" +
                "2017년에 1기가 창단되었습니다.","아이유","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/551e608d-d579-4d30-adb0-0d2c2120ba63");
        new Fanclub("아미","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%A9%ED%83%84%EC%86%8C%EB%85%84%EB%8B%A8+%ED%8C%AC%EB%8D%A4_%EC%95%84%EB%AF%B8.png","방탄소년단 공식 팬클럽.\n" +
                "2014년에 1기가 창단되었습니다.","방탄소년단","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%A9%ED%83%84%EC%86%8C%EB%85%84%EB%8B%A8+%ED%8C%AC%EB%8D%A4_%EC%95%84%EB%AF%B8.png");
        new Fanclub("피어나","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EB%A5%B4%EC%84%B8%EB%9D%BC%ED%95%8C+%ED%8C%AC%EB%8D%A4_%ED%94%BC%EC%96%B4%EB%82%98+.png","르세라핌 팬클럽.\n" +
                "2023년에 1기가 창단되었습니다.","르세라핌","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/LESSERAFIM.png");
        new Fanclub("네버랜드","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%EC%9D%B4%EB%93%A4+%ED%8C%AC%EB%8D%A4_%EB%84%A4%EB%B2%84%EB%9E%9C%EB%93%9C.png","아이들 팬클럽.\n" +
                "2018년에 1기가 창단되었습니다.","아이들","https://plowithmebucket.s3.ap-northeast-2.amazonaws.com/%EC%95%84%ED%8B%B0%EC%8A%A4%ED%8A%B8_%EC%95%84%EC%9D%B4%EB%93%A4.png");
    }
}

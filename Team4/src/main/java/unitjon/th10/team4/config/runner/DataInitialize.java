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
        new Fanclub("이너써클","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/5ebfd0a2-e025-4786-a582-2f330765a317","위너 팬클럽.\n" +
                "2014년에 1기가 창단되었습니다.","위너","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/551e608d-d579-4d30-adb0-0d2c2120ba63");
        new Fanclub("마이","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/357ea987-c24c-4d1c-bfe3-9d9bff44651f","에스파 팬클럽.\n" +
                "2020년에 1기가 창단되었습니다.","에스파","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/e3a16fe3-57cf-4a14-8d4f-31a2c4d5a4c5");
        new Fanclub("샤이니월드","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/dc2e4c96-a848-4c74-9dce-efcf6adf6937","샤이니 팬클럽.\n" +
                "2009년에 1기가 창단되었습니다.","샤이니","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/48e020db-48c7-4268-851c-3caa08064fb8");
        new Fanclub("엑소엘","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/fd8f6454-5d54-4770-8f64-1566be664423","엑소 공식 팬클럽.\n" +
                "2014년에 1기가 창단되었습니다.","엑소","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/5e3dfcfe-8558-4517-a910-0dbf740346a8");
        new Fanclub("스테이","","스트레이키즈 팬클럽.\n" +
                "2017년에 1기가 창단되었습니다.","스트레이키즈","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/551e608d-d579-4d30-adb0-0d2c2120ba63");
        new Fanclub("유애나","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/5ebfd0a2-e025-4786-a582-2f330765a317","아이유 공식 팬클럽.\n" +
                "2017년에 1기가 창단되었습니다.","아이유","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/551e608d-d579-4d30-adb0-0d2c2120ba63");
        new Fanclub("아미","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/5ebfd0a2-e025-4786-a582-2f330765a317","방탄소년단 공식 팬클럽.\n" +
                "2014년에 1기가 창단되었습니다.","방탄소년단","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/551e608d-d579-4d30-adb0-0d2c2120ba63");
        new Fanclub("피어나","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/5ebfd0a2-e025-4786-a582-2f330765a317","르세라핌 팬클럽.\n" +
                "2023년에 1기가 창단되었습니다.","르세라핌","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/551e608d-d579-4d30-adb0-0d2c2120ba63");
        new Fanclub("네버랜드","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/5ebfd0a2-e025-4786-a582-2f330765a317","아이들 팬클럽.\n" +
                "2018년에 1기가 창단되었습니다.","아이들","https://github.com/seonghoo1217/TodayILearnd/assets/39437170/551e608d-d579-4d30-adb0-0d2c2120ba63");
    }
}

package unitjon.th10.team4.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class FanclubSaveDto {

    private String name;
    private MultipartFile logoImage;
    private String fanclubInfo;
    private String artistName;
    private MultipartFile artistImage;
    private int point;
}

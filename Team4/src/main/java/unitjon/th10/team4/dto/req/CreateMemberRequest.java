package unitjon.th10.team4.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class CreateMemberRequest {

    private String name;
    private String fanclubId;
    private MultipartFile profileImage;
    private double latitude;
    private double longitude;
    private String fcmToken;
}

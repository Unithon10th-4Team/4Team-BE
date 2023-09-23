package unitjon.th10.team4.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
public class FanclubSaveDto {

    private String name;
    private MultipartFile logo;
    private String fanclub_info;
    private String artist;
    private MultipartFile artist_image;
    private String point;
}
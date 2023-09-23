package unitjon.th10.team4.dto.res;


import lombok.AllArgsConstructor;
import lombok.Data;
import unitjon.th10.team4.entity.Fanclub;

@Data
@AllArgsConstructor
public class FanclubFindDto {

    private String fanclubId;
    private String name;
    private String logoUrl;
    private String fanclubInfo;
    private String artist;
    private String artistUrl;
    private int point;

    public static FanclubFindDto toDto(Fanclub fanclub) {
        return new FanclubFindDto(
                fanclub.getFanclubId(),
                fanclub.getName(),
                fanclub.getLogoImageUrl(),
                fanclub.getFanclubInfo(),
                fanclub.getArtistName(),
                fanclub.getArtistImageUrl(),
                fanclub.getPoint()
        );
    }
}


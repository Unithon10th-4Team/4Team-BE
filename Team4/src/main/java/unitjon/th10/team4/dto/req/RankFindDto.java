package unitjon.th10.team4.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import unitjon.th10.team4.dto.res.FanclubFindDto;
import unitjon.th10.team4.entity.Fanclub;
@Data
@AllArgsConstructor
public class RankFindDto {


    public static FanclubFindDto toDto(Fanclub fanclub) {
        return new FanclubFindDto(
                fanclub.getFanclubId(),
                fanclub.getName(),
                fanclub.getLogoUrl(),
                fanclub.getFanclubInfo(),
                fanclub.getArtist(),
                fanclub.getArtistUrl(),
                fanclub.getPoint()
        );
    }

}

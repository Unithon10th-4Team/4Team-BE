package unitjon.th10.team4.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import unitjon.th10.team4.global.entity.Fanclub;
@Data
@AllArgsConstructor
public class RankFindDto {


    public static FanclubFindDto toDto(Fanclub fanclub) {
        return new FanclubFindDto(fanclub.getFanclub_id(),fanclub.getName(), fanclub.getLogo_url(), fanclub.getFanclub_info(), fanclub.getArtist(),fanclub.getArtist_url(),fanclub.getPoint());}

}

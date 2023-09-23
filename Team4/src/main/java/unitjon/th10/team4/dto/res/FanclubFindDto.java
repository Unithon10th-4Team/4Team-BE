package unitjon.th10.team4.dto.res;


import lombok.AllArgsConstructor;
import lombok.Data;
import unitjon.th10.team4.entity.Fanclub;

@Data
@AllArgsConstructor
public class FanclubFindDto {

    private String fanclub_id;
    private String name;
    private String logo_url;
    private String fanclub_info;
    private String artist;
    private String artist_url;
    private String point;


    public static FanclubFindDto toDto(Fanclub fanclub) {
        return new FanclubFindDto(fanclub.getFanclubId(),fanclub.getName(), fanclub.getLogoUrl(), fanclub.getFanclubInfo(), fanclub.getArtist(),fanclub.getArtistUrl(),fanclub.getPoint());}



}


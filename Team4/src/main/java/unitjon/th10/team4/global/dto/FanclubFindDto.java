package unitjon.th10.team4.global.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import unitjon.th10.team4.global.entity.Fanclub;

import java.util.List;

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
        return new FanclubFindDto(fanclub.getFanclub_id(),fanclub.getName(), fanclub.getLogo_url(), fanclub.getFanclub_info(), fanclub.getArtist(),fanclub.getArtist_url(),fanclub.getPoint());}



}


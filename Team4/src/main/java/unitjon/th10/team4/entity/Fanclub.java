package unitjon.th10.team4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@RedisHash(value = "fanclub")
public class Fanclub implements Serializable {

    @Id
    private String fanclubId;
    private String name;
    private String logoImageUrl;
    private String fanclubInfo;
    private String artistName;
    private String artistImageUrl;
    private int point;

    public Fanclub(String name, String logoImageUrl, String fanclubInfo, String artistName, String artistImageUrl) {
        this.fanclubId = UUID.randomUUID().toString();
        this.name = name;
        this.logoImageUrl = logoImageUrl;
        this.fanclubInfo = fanclubInfo;
        this.artistName = artistName;
        this.artistImageUrl = artistImageUrl;
    }
}

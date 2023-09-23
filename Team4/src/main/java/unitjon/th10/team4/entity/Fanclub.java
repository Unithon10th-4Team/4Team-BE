package unitjon.th10.team4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "fanclub")
public class Fanclub implements Serializable {

    @Id
    private String fanclubId;
    private String name;
    private String logoUrl;
    private String fanclubInfo;
    private String artist;
    private String artistUrl;
    private String point;
}

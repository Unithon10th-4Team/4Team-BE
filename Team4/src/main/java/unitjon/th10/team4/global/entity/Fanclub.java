package unitjon.th10.team4.global.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "fanclub")
public class Fanclub implements Serializable {

    @Id
    private String fanclub_id;
    private String name;
    private String logo_url;
    private String fanclub_info;
    private String artist;
    private String artist_url;
    private String point;


}

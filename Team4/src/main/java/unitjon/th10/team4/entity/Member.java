package unitjon.th10.team4.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.GeoIndexed;

import java.io.Serializable;

@Setter
@Getter
@RedisHash("member")
public class Member implements Serializable {

    @Id
    private String name;
    private String fanclubId;
    private String profileImageUrl;
    private int point;
    @GeoIndexed
    private Point location;
    private boolean isOnline;
    private String fcmToken;

    public Member(String name, String fanclubId, String profileImageUrl, Point location, String fcmToken) {
        this.name = name;
        this.fanclubId = fanclubId;
        this.profileImageUrl = profileImageUrl;
        this.location = location;
        this.fcmToken = fcmToken;
        this.point = 0;
        this.isOnline = true;
    }
}

package unitjon.th10.team4.global.repository;

import org.springframework.data.repository.CrudRepository;
import unitjon.th10.team4.global.entity.Fanclub;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

public interface FanclubRepository extends CrudRepository<Fanclub,String> {
    Fanclub save(Fanclub fanclub);
    Optional<Fanclub> findById(String id);
    void delete(String id);

    List<Fanclub> findAll(String id);

}

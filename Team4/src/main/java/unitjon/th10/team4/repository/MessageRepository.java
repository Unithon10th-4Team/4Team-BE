package unitjon.th10.team4.repository;

import org.springframework.data.repository.CrudRepository;
import unitjon.th10.team4.entity.Message;

public interface MessageRepository extends CrudRepository<Message,String> {
}

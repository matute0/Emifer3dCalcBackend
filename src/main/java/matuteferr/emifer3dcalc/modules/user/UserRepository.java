package matuteferr.emifer3dcalc.modules.user;

import matuteferr.emifer3dcalc.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

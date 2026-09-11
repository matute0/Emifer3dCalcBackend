package matuteferr.emifer3dcalc.modules.user;

import matuteferr.emifer3dcalc.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    Optional<User> findByUsername(String username);
}

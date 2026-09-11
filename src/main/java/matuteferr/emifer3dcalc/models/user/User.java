package matuteferr.emifer3dcalc.models.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String email;
    private String username;
    private String password;
}

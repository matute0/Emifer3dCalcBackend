package matuteferr.emifer3dcalc.models.user;

import matuteferr.emifer3dcalc.models.user.dtos.GetUserDTO;
import matuteferr.emifer3dcalc.modules.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private UserRepository userRepository;

    public GetUserDTO UserToGet(User user){
        return GetUserDTO.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}

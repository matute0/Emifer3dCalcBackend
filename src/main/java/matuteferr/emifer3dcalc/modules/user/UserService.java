package matuteferr.emifer3dcalc.modules.user;

import matuteferr.emifer3dcalc.models.user.User;
import matuteferr.emifer3dcalc.models.user.UserMapper;
import matuteferr.emifer3dcalc.models.user.dtos.POSTUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserValidations userValidations;

    public String register(POSTUserDTO userPost){
        userValidations.validate(userPost);
        String passwordBCrypt = passwordEncoder.encode(userPost.getPassword());
        User user = userMapper.PostToUser(userPost);
        user.setPassword(passwordBCrypt);
        userRepository.save(user);
        return "User Register";
    }
}

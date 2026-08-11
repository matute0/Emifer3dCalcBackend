package matuteferr.emifer3dcalc.config;

import matuteferr.emifer3dcalc.models.user.User;
import matuteferr.emifer3dcalc.modules.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String email;
    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;
    @Override
    public void run(String... args) {
        if(!userRepository.existsByEmail(email)){
            User admin = User.builder()
                    .email(email)
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .build();
            userRepository.save(admin);
        }
    }
}

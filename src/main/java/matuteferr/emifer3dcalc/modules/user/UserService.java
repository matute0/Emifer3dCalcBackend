package matuteferr.emifer3dcalc.modules.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import matuteferr.emifer3dcalc.models.user.User;
import matuteferr.emifer3dcalc.models.user.UserMapper;
import matuteferr.emifer3dcalc.models.user.dtos.GetUserDTO;
import matuteferr.emifer3dcalc.models.user.dtos.POSTLoginDTO;
import matuteferr.emifer3dcalc.models.user.dtos.POSTUserDTO;
import matuteferr.emifer3dcalc.security.jwt.CustomUserDetailsService;
import matuteferr.emifer3dcalc.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, UserRepository userRepository, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Value("${is.production}")
    private boolean isProduction;

    public String login(POSTLoginDTO postLoginDTO, HttpServletResponse response){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(postLoginDTO.getUsername(), postLoginDTO.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(postLoginDTO.getUsername());
        final String jwt = jwtTokenUtil.generateToken(postLoginDTO.getUsername(), userDetails);

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(isProduction);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cookie);

        return "Login Successfully";
    }
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie deleteCookie = new Cookie("jwt", null);
        deleteCookie.setHttpOnly(true);
        deleteCookie.setSecure(true);
        deleteCookie.setPath("/");
        deleteCookie.setMaxAge(0);
        response.addCookie(deleteCookie);
        jwtTokenUtil.logout(request);
        return "Logged out";
    }
    public boolean validate(HttpServletRequest request){
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("jwt")){
                    return jwtTokenUtil.validateToken(cookie.getValue(), userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(cookie.getValue()))) || !jwtTokenUtil.getBlackList().contains(cookie.getValue());
                }
            }
        }
        return false;
    }
    public GetUserDTO getUserAuth(HttpServletRequest request){
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("jwt")){
                    if(validate(request)){
                        Optional<User> user = userRepository.findByUsername(jwtTokenUtil.extractUsername(cookie.getValue()));
                        return userMapper.UserToGet(user.get());
                    }
                }
            }
        }
        return null;
    }
}

package matuteferr.emifer3dcalc.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import matuteferr.emifer3dcalc.models.user.UserMapper;
import matuteferr.emifer3dcalc.modules.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private Key secretKey;

    @Value("${jwt.secret}")
    private String secretString;

    @PostConstruct
    public void init(){
        this.secretKey = Keys.hmacShaKeyFor(secretString.getBytes());
    }
    @Getter
    private final Set<String> blackList = new HashSet<>();

    public String generateToken(String username, UserDetails userDetails){
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (24*60*60)*1000))
                .signWith(secretKey)
                .compact();
    }
    public void logout(HttpServletRequest request){
        if(request.getCookies() != null){
            for (Cookie cookie: request.getCookies()){
                if("jwt".equals(cookie.getName())){
                    blackList.add(cookie.getValue());
                    cookie.setMaxAge(0);
                }
            }
        }
    }
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
}

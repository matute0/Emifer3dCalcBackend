package matuteferr.emifer3dcalc.modules.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matuteferr.emifer3dcalc.models.user.dtos.POSTLoginDTO;
import matuteferr.emifer3dcalc.models.user.dtos.POSTUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints for user login, session management, and logout operations.")
public class UserController {
    @Autowired
    private UserService userService;
    @Operation(
            summary = "User Login",
            description = "Authenticates user credentials and sets an HTTP-only JWT cookie upon successful login."
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody POSTLoginDTO postLoginDTO, HttpServletResponse response){
        return ResponseEntity.ok(userService.login(postLoginDTO, response));
    }
    @Operation(
            summary = "User Logout",
            description = "Clears the JWT cookie from the client and terminates the current user session."
    )
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(userService.logout(request, response));
    }
    @PatchMapping("/validate")
    public ResponseEntity<Boolean> validate(HttpServletRequest request){
        return ResponseEntity.ok(userService.validate(request));
    }

}

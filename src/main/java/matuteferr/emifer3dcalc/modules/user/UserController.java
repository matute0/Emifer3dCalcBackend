package matuteferr.emifer3dcalc.modules.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import matuteferr.emifer3dcalc.models.user.dtos.POSTUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User Managment")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account in the system. Requires a unique username (4-32 characters, including at least one number), a valid email address, and a strong password. Returns the registered user details upon success."
    )
    public ResponseEntity<String> register(@RequestBody POSTUserDTO postUserDTO){
        return ResponseEntity.ok(userService.register(postUserDTO));
    }
}

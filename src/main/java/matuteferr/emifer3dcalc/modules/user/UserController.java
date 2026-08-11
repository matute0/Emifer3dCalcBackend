package matuteferr.emifer3dcalc.modules.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matuteferr.emifer3dcalc.models.user.dtos.POSTLoginDTO;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody POSTLoginDTO postLoginDTO, HttpServletResponse response){
        return ResponseEntity.ok(userService.login(postLoginDTO, response));
    }
    @PatchMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(userService.logout(request, response));
    }

}

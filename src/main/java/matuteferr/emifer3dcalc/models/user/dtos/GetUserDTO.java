package matuteferr.emifer3dcalc.models.user.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserDTO {
    private String email;
    private String username;
}

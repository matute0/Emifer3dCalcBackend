package matuteferr.emifer3dcalc.models.filament.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GETFilamentDTO {
    private String id;
    @NotBlank
    private String colour;
    @NotBlank
    private String type;
    @NotBlank
    private String manufacturer;
    @NotBlank
    private boolean status;
}

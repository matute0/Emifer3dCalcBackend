package matuteferr.emifer3dcalc.models.filament.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilamentDTO {
    @NotBlank
    private String colour;
    @NotBlank
    private String type;
    @NotBlank
    private String manufacturer;
    private int price;
}

package matuteferr.emifer3dcalc.models.filament.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilamentAmountDTO {
    private String filamentID;
    private int amount;
}

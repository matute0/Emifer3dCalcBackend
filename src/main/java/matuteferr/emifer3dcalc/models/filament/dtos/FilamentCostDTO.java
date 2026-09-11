package matuteferr.emifer3dcalc.models.filament.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilamentCostDTO {
    private String filamentID;
    private int amount;
    private int finalCost;
}

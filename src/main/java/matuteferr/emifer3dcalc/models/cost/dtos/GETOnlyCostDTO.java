package matuteferr.emifer3dcalc.models.cost.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import matuteferr.emifer3dcalc.models.cost.addCost.AdditionalCost;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GETOnlyCostDTO {
    public int filamentCost;
    public int printerCost;
    public List<AdditionalCost> additionalCost;
    public int finalCost;
}

package matuteferr.emifer3dcalc.models.cost.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import matuteferr.emifer3dcalc.models.cost.addCost.AdditionalCost;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentCostDTO;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterCost;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GETOnlyCostDTO {
    public List<FilamentCostDTO> filamentCosts;
    public GETPrinterCost printerCost;
    public List<AdditionalCost> additionalCost;
    public int profit;
    public int finalCost;
}

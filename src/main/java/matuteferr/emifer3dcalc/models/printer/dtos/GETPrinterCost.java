package matuteferr.emifer3dcalc.models.printer.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GETPrinterCost {
    private int wattsCost;
    private int wearCostPrint;
}

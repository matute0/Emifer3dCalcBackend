package matuteferr.emifer3dcalc.models.printer.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class POSTPrinterDTO {
    private String name;
    private String manufacturer;
    private int watts;
    private boolean multiColour;
    private int wearCost;
}

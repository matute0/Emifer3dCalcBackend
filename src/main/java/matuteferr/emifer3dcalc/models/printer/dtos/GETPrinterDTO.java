package matuteferr.emifer3dcalc.models.printer.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GETPrinterDTO {
    private String name;
    private String manufacturer;
    private int watts;
    private List<Double> nozzles;
    private boolean multiColour;    
    private int wearCost;
    private boolean available;
}

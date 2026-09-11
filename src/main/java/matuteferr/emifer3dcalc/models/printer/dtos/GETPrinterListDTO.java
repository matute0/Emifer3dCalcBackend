package matuteferr.emifer3dcalc.models.printer.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GETPrinterListDTO {
    private String id;
    private String name;
    private String manufacturer;
    private int watts;
    private boolean multiColour;
}

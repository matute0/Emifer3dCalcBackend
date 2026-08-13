package matuteferr.emifer3dcalc.models.cost.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentAmountDTO;

import java.time.Duration;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class POSTCostDTO {
    private String printerID;
    private List<FilamentAmountDTO> filamentAMList;
    private int hours;
    private int minutes;
}

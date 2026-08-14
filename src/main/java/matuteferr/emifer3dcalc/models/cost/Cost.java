package matuteferr.emifer3dcalc.models.cost;

import lombok.Builder;
import lombok.Data;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentAmountDTO;
import matuteferr.emifer3dcalc.models.printer.Printer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.List;

@Document(collection = "costs")
@Data
@Builder
public class Cost {
    @Id
    private String id;
    private String printerID;
    private List<FilamentAmountDTO> filamentAMList;
    private Duration printTime;
    private List<Integer> additionalCosts;
    private int finalCost;
}

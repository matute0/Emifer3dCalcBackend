package matuteferr.emifer3dcalc.models.printer;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document(collection = "printers")
public class Printer {
    @Id
    private String id;
    private String name;
    private String manufacturer;
    private int watts;
    private List<Double> nozzles;
    private boolean multiColour;
    private int wearCost;
    private boolean available;
}

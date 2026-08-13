package matuteferr.emifer3dcalc.models.cost;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "costConfigs")
public class CostConfig {
    @Id
    private String id;
    private int profitPercentage;
    private int kwhCost;
}

package matuteferr.emifer3dcalc.models.cost.addCost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalCost {
    private String costName;
    private int unitPrice;
    private int quantity;

    public int getTotalCost(){
        return unitPrice * quantity;
    }
}

package matuteferr.emifer3dcalc.models.cost.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GETOnlyCostDTO {
    public int finalCost;
}

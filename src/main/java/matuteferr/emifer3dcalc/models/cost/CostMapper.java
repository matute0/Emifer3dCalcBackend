package matuteferr.emifer3dcalc.models.cost;

import matuteferr.emifer3dcalc.models.cost.dtos.GETOnlyCostDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostConfigDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CostMapper {
    public Cost POSTToCost(POSTCostDTO costDTO){
        return Cost.builder()
                .printTime(Duration.ofHours(costDTO.getHours()).plusMinutes(costDTO.getMinutes()))
                .filamentAMList(costDTO.getFilamentAMList())
                .printerID(costDTO.getPrinterID())
                .additionalCosts(costDTO.getAdditonalCosts())
                .build();
    }
    public GETOnlyCostDTO CostToGet(Cost cost){
        return GETOnlyCostDTO.builder()
                .finalCost(cost.getFinalCost())
                .build();
    }
    public POSTCostConfigDTO costToDTO(CostConfig config){
        return POSTCostConfigDTO.builder()
                .khwCost(config.getKwhCost())
                .profitPercentage(config.getProfitPercentage())
                .build();
    }
}

package matuteferr.emifer3dcalc.models.cost;

import matuteferr.emifer3dcalc.models.cost.addCost.AdditionalCost;
import matuteferr.emifer3dcalc.models.cost.dtos.GETOnlyCostDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTAdditionalCost;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostConfigDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentCostDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class CostMapper {
    public Cost POSTToCost(POSTCostDTO costDTO){
        return Cost.builder()
                .printTime(Duration.ofHours(costDTO.getHours()).plusMinutes(costDTO.getMinutes()))
                .filamentAMList(costDTO.getFilamentAMList())
                .printerID(costDTO.getPrinterID())
                .additionalCosts(costDTO.getAdditionalCosts())
                .build();
    }
    public GETOnlyCostDTO CostToGet(int finalCost, List<POSTAdditionalCost> additionalCost, List<FilamentCostDTO> filamentCost, int printerCost){
        return GETOnlyCostDTO.builder()
                .finalCost(finalCost)
                .additionalCost(POSTDTOListAdd(additionalCost))
                .filamentCosts(filamentCost)
                .printerCost(printerCost)
                .build();
    }
    public POSTCostConfigDTO costToDTO(CostConfig config){
        return POSTCostConfigDTO.builder()
                .khwCost(config.getKwhCost())
                .profitPercentage(config.getProfitPercentage())
                .build();
    }
    public AdditionalCost PostAddToEntity(POSTAdditionalCost postAdditionalCost){
        return AdditionalCost.builder()
                .costName(postAdditionalCost.getCostName())
                .quantity(postAdditionalCost.getQuantity())
                .unitPrice(postAdditionalCost.getUnitPrice())
                .build();
    }
    public List<AdditionalCost> POSTDTOListAdd(List<POSTAdditionalCost> postAdditionalCostList){
        List<AdditionalCost> list = new ArrayList<>();
        for(POSTAdditionalCost additionalCostDTO : postAdditionalCostList){
            AdditionalCost newAdd = PostAddToEntity(additionalCostDTO);
            list.add(newAdd);
        }
        return list;
    }
}

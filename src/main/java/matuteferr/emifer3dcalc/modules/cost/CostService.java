package matuteferr.emifer3dcalc.modules.cost;

import matuteferr.emifer3dcalc.models.cost.Cost;
import matuteferr.emifer3dcalc.models.cost.CostConfig;
import matuteferr.emifer3dcalc.models.cost.CostMapper;
import matuteferr.emifer3dcalc.models.cost.dtos.GETOnlyCostDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostConfigDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentAmountDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.GETFilamentDTO;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterDTO;
import matuteferr.emifer3dcalc.modules.filament.FilamentService;
import matuteferr.emifer3dcalc.modules.printer.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class CostService {
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private PrinterService printerService;
    @Autowired
    private FilamentService filamentService;
    @Autowired
    private CostMapper costMapper;
    @Autowired
    private CostConfigRepository costConfigRepository;

    public GETOnlyCostDTO calcFinalCost(POSTCostDTO postCostDTO){
        GETPrinterDTO printer = printerService.getByID(postCostDTO.getPrinterID());
        Cost cost = costMapper.POSTToCost(postCostDTO);
        POSTCostConfigDTO config = costMapper.costToDTO(costConfigRepository.findAll().get(0));
        cost.setFinalCost(cost.getFinalCost()
                + filamentCost(postCostDTO.getFilamentAMList())
                + printerCost(printer, cost.getPrintTime())
                + additionalCost(postCostDTO));

        cost.setFinalCost(cost.getFinalCost()*((100 + config.getProfitPercentage())/100));

        costRepository.save(cost);
        return costMapper.CostToGet(cost);
    }
    public int filamentCost(List<FilamentAmountDTO> filamentAmountDTOList){
        int price = 0;
        for(FilamentAmountDTO filamentAM: filamentAmountDTOList){
            GETFilamentDTO filament = filamentService.getByID(filamentAM.getFilamentID());
            double filamentCost = ((double) filamentAM.getAmount() /1000)*filament.getPrice();
            price += ((int) Math.round(filamentCost));
        }

        return price;
    }
    public int printerCost(GETPrinterDTO printer, Duration duration){
        double totalHoursDecimal = duration.toMinutes() / 60.0;
        return (int) Math.round((((double) printer.getWatts() /1000)*totalHoursDecimal* getConfig().getKhwCost())+(((double) printer.getWearCost() /100) * totalHoursDecimal));
    }
    public int additionalCost(POSTCostDTO postCostDTO){
        int price = 0;
        for(Integer cost: postCostDTO.getAdditonalCosts()){
            price += cost;
        }
        return price;
    }
    public POSTCostConfigDTO getConfig(){
        return costMapper.costToDTO(costConfigRepository.findAll().get(0));
    }
    public String updateConfig(POSTCostConfigDTO config){
        CostConfig config1 = costConfigRepository.findAll().get(0);
        config1.setKwhCost(config.getKhwCost());
        config1.setProfitPercentage(config.getProfitPercentage());
        costConfigRepository.save(config1);
        return "Config updated";
    }
}

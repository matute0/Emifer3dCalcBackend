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
        for(FilamentAmountDTO filamentAmountDTO : postCostDTO.getFilamentAMList()){
            GETFilamentDTO filament = filamentService.getByID(filamentAmountDTO.getFilamentID());
            // hacer llamado a la api de mercado libre
            double filamentCost = ((double) filamentAmountDTO.getAmount() /1000)*600;
            cost.setFinalCost(cost.getFinalCost() + ((int) Math.round(filamentCost)));
        }
        cost.setFinalCost((int)((cost.getFinalCost() + printerCost(printer, cost.getPrintTime()))*((100 + config.getProfitPercentage())/100)));
        costRepository.save(cost);
        return costMapper.CostToGet(cost);
    }
    public int printerCost(GETPrinterDTO printer, Duration duration){
        double totalHoursDecimal = duration.toMinutes() / 60.0;
        return (int) Math.round((((double) printer.getWatts() /1000)*totalHoursDecimal* getConfig().getKhwCost())+(((double) printer.getWearCost() /100) * totalHoursDecimal));
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

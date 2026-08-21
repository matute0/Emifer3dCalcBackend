package matuteferr.emifer3dcalc.modules.cost;

import matuteferr.emifer3dcalc.models.cost.Cost;
import matuteferr.emifer3dcalc.models.cost.CostConfig;
import matuteferr.emifer3dcalc.models.cost.CostMapper;
import matuteferr.emifer3dcalc.models.cost.addCost.AdditionalCost;
import matuteferr.emifer3dcalc.models.cost.dtos.GETOnlyCostDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTAdditionalCost;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostConfigDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostDTO;
import matuteferr.emifer3dcalc.models.filament.Filament;
import matuteferr.emifer3dcalc.models.filament.FilamentMapper;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentAmountDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentCostDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.GETFilamentDTO;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterCost;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterDTO;
import matuteferr.emifer3dcalc.modules.filament.FilamentService;
import matuteferr.emifer3dcalc.modules.printer.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
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
    @Autowired
    private FilamentMapper filamentMapper;

    public GETOnlyCostDTO calcFinalCost(POSTCostDTO postCostDTO){
        GETPrinterDTO printer = printerService.getByID(postCostDTO.getPrinterID());
        Cost cost = costMapper.POSTToCost(postCostDTO);
        POSTCostConfigDTO config = costMapper.costToDTO(costConfigRepository.findAll().get(0));
        List<FilamentCostDTO> filamentCostList = filamentCost(postCostDTO.getFilamentAMList());
        double totalHoursDecimal = cost.getPrintTime().toMinutes() / 60.0;
        GETPrinterCost printerCostDTO = GETPrinterCost.builder()
                .wattsCost(getWattsCost(printer, totalHoursDecimal))
                .wearCostPrint(getWearCost(printer, totalHoursDecimal))
                .build();
        int filamentCost = totalFilamentCost(filamentCostList);
        int printerCost = printerCost(printer, totalHoursDecimal);
        int additionalCost = additionalCost(postCostDTO);
        cost.setFinalCost(cost.getFinalCost()
                + filamentCost
                + printerCost
                + additionalCost
        );

        int profit = (int) Math.round(cost.getFinalCost() * ((config.getProfitPercentage()) / 100.0));

        cost.setFinalCost(cost.getFinalCost() + profit);

        costRepository.save(cost);
        return costMapper.CostToGet(profit, cost.getFinalCost(), cost.getAdditionalCosts(), filamentCostList, printerCostDTO);
    }
    public int totalFilamentCost(List<FilamentCostDTO> filamentCostList){
        int price = 0;
        for(FilamentCostDTO filamentCostDTO : filamentCostList){
            price += filamentCostDTO.getFinalCost();
        }
        return price;
    }
    public List<FilamentCostDTO> filamentCost(List<FilamentAmountDTO> filamentAmountDTOList){
        List<FilamentCostDTO> list = new ArrayList<>();
        for(FilamentAmountDTO filamentAM: filamentAmountDTOList){
            FilamentCostDTO filamentCostDTO = filamentMapper.filamentToCost(filamentAM);
            GETFilamentDTO filament = filamentService.getByID(filamentAM.getFilamentID());
            filamentCostDTO.setFinalCost(Math.round(((float) filamentAM.getAmount() /1000) *filament.getPrice()));
            list.add(filamentCostDTO);
        }
        return list;
    }
    public int printerCost(GETPrinterDTO printer, double totalHoursDecimal){
        return getWattsCost(printer, totalHoursDecimal) + getWearCost(printer, totalHoursDecimal);
    }
    public int getWearCost(GETPrinterDTO printer, double totalHoursDecimal){
        return (int) Math.round((((double) printer.getWearCost() /100) * totalHoursDecimal));
    }
    public int getWattsCost(GETPrinterDTO printer, double totalHoursDecimal){
        return (int) Math.round((((double) printer.getWatts() /1000)*totalHoursDecimal* getConfig().getKhwCost()));
    }
    public int additionalCost(POSTCostDTO postCostDTO){
        int price = 0;
        for(POSTAdditionalCost cost: postCostDTO.getAdditionalCosts()){
            AdditionalCost tempCost = costMapper.PostAddToEntity(cost);
            price += tempCost.getTotalCost();
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

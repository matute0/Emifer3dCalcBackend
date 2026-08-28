package matuteferr.emifer3dcalc.modules.cost;

import matuteferr.emifer3dcalc.exceptions.*;
import matuteferr.emifer3dcalc.models.cost.CostConfig;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTAdditionalCost;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostConfigDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentAmountDTO;
import matuteferr.emifer3dcalc.modules.filament.FilamentRepository;
import matuteferr.emifer3dcalc.modules.printer.PrinterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CostValidations {
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private PrinterRepository printerRepository;
    @Autowired
    private FilamentRepository filamentRepository;

    public void validate(POSTCostDTO costDTO){
        if(!printerExists(costDTO.getPrinterID())){
            throw new PrinterNotFoundException();
        }
        if(!filamentValidate(costDTO.getFilamentAMList())){
            throw new FilamentException();
        }
        if(!additionalCostsValidate(costDTO.getAdditionalCosts())){
            throw new AdditionalCostException();
        }
        if(!durationValidate(costDTO.getHours(), costDTO.getMinutes())){
            throw new DurationException();
        }
    }

    public boolean printerExists(String printerId){
        return printerRepository.existsById(printerId);
    }
    public boolean filamentValidate(List<FilamentAmountDTO> filamentAmountDTOList){
        for(FilamentAmountDTO filamentAmountDTO : filamentAmountDTOList){
            if(filamentRepository.existsById(filamentAmountDTO.getFilamentID())){
                if(filamentAmountDTO.getAmount() > 0){
                    continue;
                }
            }
            return false;
        }
        return true;
    }
    public boolean additionalCostsValidate(List<POSTAdditionalCost> additionalCosts){
        for(POSTAdditionalCost additionalCost : additionalCosts){
            if(additionalCost.getCostName().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$")){
                if(!additionalCost.getCostName().trim().isEmpty()){
                    if(additionalCost.getQuantity() > 0){
                        if(additionalCost.getUnitPrice() > 0){
                            continue;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }
    public boolean durationValidate(int hours, int minutes){
        return hours >= 0 && minutes >= 0 && (hours > 0 || minutes > 0);
    }
    public void configValidate(POSTCostConfigDTO costConfigDTO){
        if(costConfigDTO.getKhwCost() > 0 || costConfigDTO.getProfitPercentage() > 0){
            throw new CostConfigException();
        }
    }
}

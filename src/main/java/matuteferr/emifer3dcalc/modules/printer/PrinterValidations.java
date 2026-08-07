package matuteferr.emifer3dcalc.modules.printer;

import matuteferr.emifer3dcalc.exceptions.ManufacturerFormatException;
import matuteferr.emifer3dcalc.exceptions.NameFormatException;
import matuteferr.emifer3dcalc.exceptions.WattsException;
import matuteferr.emifer3dcalc.models.printer.Printer;
import matuteferr.emifer3dcalc.models.printer.dtos.POSTPrinterDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrinterValidations {

    private boolean validateName(String name){
        return name.matches("^[a-zA-Z0-9\\s\\-_.+]{2,50}$");
    }
    private boolean validateManufacturer(String manufacturer){
        return manufacturer.matches("^[a-zA-Z0-9\\s\\-]{2,30}$");
    }
    private boolean validateWatts(int watts) {
        if (watts <= 0) {
            return false;
        } else if (watts > 9999) {
            return false;
        }
        return true;
    }
    public void validation(POSTPrinterDTO printerDTO){
        if(!validateName(printerDTO.getName())){
            throw new NameFormatException();
        }
        if(!validateManufacturer(printerDTO.getManufacturer())){
            throw new ManufacturerFormatException();
        }
        if(!validateWatts(printerDTO.getWatts())){
            throw new WattsException();
        }
    }
}

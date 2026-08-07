package matuteferr.emifer3dcalc.models.printer;

import matuteferr.emifer3dcalc.models.printer.dtos.POSTPrinterDTO;
import org.springframework.stereotype.Component;

@Component
public class PrinterMapper {

    public Printer dtoToPrinter(POSTPrinterDTO printerDTO){
        return Printer.builder()
                .name(printerDTO.getName())
                .watts(printerDTO.getWatts())
                .nozzles(printerDTO.getNozzles())
                .manufacturer(printerDTO.getManufacturer())
                .wearCost(printerDTO.getWearCost())
                .multiColour(printerDTO.isMultiColour())
                .build();
    }
}

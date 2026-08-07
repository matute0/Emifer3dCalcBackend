package matuteferr.emifer3dcalc.modules.printer;

import matuteferr.emifer3dcalc.models.printer.Printer;
import matuteferr.emifer3dcalc.models.printer.PrinterMapper;
import matuteferr.emifer3dcalc.models.printer.dtos.POSTPrinterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrinterService {
    @Autowired
    private PrinterRepository printerRepository;
    @Autowired
    private PrinterMapper printerMapper;
    @Autowired
    private PrinterValidations printerValidations;

    public String register(POSTPrinterDTO printerDTO){
        printerValidations.validation(printerDTO);
        Printer printer = printerMapper.dtoToPrinter(printerDTO);
        printer.setAvailable(true);
        printerRepository.save(printer);
        return "Printer registered";
    }
}

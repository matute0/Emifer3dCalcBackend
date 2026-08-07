package matuteferr.emifer3dcalc.modules.printer;

import matuteferr.emifer3dcalc.exceptions.PrinterNotFoundException;
import matuteferr.emifer3dcalc.models.printer.Printer;
import matuteferr.emifer3dcalc.models.printer.PrinterMapper;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterDTO;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterListDTO;
import matuteferr.emifer3dcalc.models.printer.dtos.POSTPrinterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<GETPrinterListDTO> list(boolean available){
        return printerRepository.findByAvailable(available);
    }
    public GETPrinterDTO getByID(String id){
        GETPrinterDTO printerDTO = printerMapper.printerToGETDTO(printerRepository.findById(id).get());
        if(printerDTO == null){
            throw new PrinterNotFoundException();
        }
        return printerDTO;
    }
}

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
import java.util.Optional;

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
        GETPrinterDTO printerDTO = printerMapper.printerToGETDTO(printerRepository.findById(id).orElseThrow(PrinterNotFoundException::new));
        if(printerDTO == null){
            throw new PrinterNotFoundException();
        }
        return printerDTO;
    }
    public String delete(String id){
        Printer printer = printerRepository.findById(id).get();
        printerRepository.delete(printer);
        return "Printer deleted";
    }
    public String update(POSTPrinterDTO printerDTO, String id){
        printerValidations.validation(printerDTO);
        Optional<Printer> printer = printerRepository.findById(id);
        if(printer.isEmpty()){
            throw new PrinterNotFoundException();
        }
        printer.get().setName(printerDTO.getName());
        printer.get().setManufacturer(printerDTO.getManufacturer());
        printer.get().setWatts(printerDTO.getWatts());
        printer.get().setWearCost(printerDTO.getWearCost());
        printer.get().setMultiColour(printerDTO.isMultiColour());
        printerRepository.save(printer.get());
        return "Printer updated";
    }
}

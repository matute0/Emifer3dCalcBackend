package matuteferr.emifer3dcalc.modules.printer;

import matuteferr.emifer3dcalc.models.printer.Printer;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterListDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrinterRepository extends MongoRepository<Printer, String> {
    List<GETPrinterListDTO> findByAvailable(boolean available);
}

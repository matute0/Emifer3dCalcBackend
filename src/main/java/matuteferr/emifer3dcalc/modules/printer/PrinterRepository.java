package matuteferr.emifer3dcalc.modules.printer;

import matuteferr.emifer3dcalc.models.printer.Printer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterRepository extends MongoRepository<Printer, String> {
}

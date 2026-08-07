package matuteferr.emifer3dcalc.modules.printer;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import matuteferr.emifer3dcalc.models.printer.dtos.POSTPrinterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/printer")
@Tag(name = "Printers", description = "CRUD operations for the system's 3D printers.")
public class PrinterController {
    @Autowired
    private PrinterService printerService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody POSTPrinterDTO printerDTO){
        return ResponseEntity.ok(printerService.register(printerDTO));
    }


}

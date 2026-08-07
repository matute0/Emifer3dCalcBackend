package matuteferr.emifer3dcalc.modules.printer;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterDTO;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterListDTO;
import matuteferr.emifer3dcalc.models.printer.dtos.POSTPrinterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/list")
    public ResponseEntity<List<GETPrinterListDTO>> list(@RequestParam boolean available){
        return ResponseEntity.ok(printerService.list(available));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<GETPrinterDTO> getByID(@PathVariable String id){
        return ResponseEntity.ok(printerService.getByID(id));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String id){
        return ResponseEntity.ok(printerService.delete(id));
    }
    @PatchMapping("/unavailable")
    public ResponseEntity<String> unavailable(@RequestParam String id){
        return ResponseEntity.ok(printerService.unavailable(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody POSTPrinterDTO printerDTO){
        return ResponseEntity.ok(printerService.update(printerDTO, id));
    }
}

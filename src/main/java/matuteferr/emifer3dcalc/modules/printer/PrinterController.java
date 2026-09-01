package matuteferr.emifer3dcalc.modules.printer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterDTO;
import matuteferr.emifer3dcalc.models.printer.dtos.GETPrinterListDTO;
import matuteferr.emifer3dcalc.models.printer.dtos.POSTPrinterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/printer")
@Tag(name = "Printers", description = "CRUD operations for the system's 3D printers.")
public class PrinterController {
    @Autowired
    private PrinterService printerService;
    @Operation(
            summary = "Register a new printer",
            description = "Adds a new 3D printer to the system catalog along with its specifications."
    )
    @PostMapping("/register")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> register(@Valid @RequestBody POSTPrinterDTO printerDTO){
        return ResponseEntity.ok(printerService.register(printerDTO));
    }
    @Operation(
            summary = "Get a list of all printers",
            description = "Retrieves a summary list containing all the 3D printers registered in the system."
    )
    @GetMapping("/list")
    public ResponseEntity<List<GETPrinterListDTO>> list(@RequestParam(defaultValue = "true") boolean available){
        return ResponseEntity.ok(printerService.list(available));
    }
    @Operation(
            summary = "Get printer details by ID",
            description = "Retrieves the full detailed information of a specific 3D printer using its unique identifier."
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<GETPrinterDTO> getByID(@PathVariable String id){
        return ResponseEntity.ok(printerService.getByID(id));
    }
    @Operation(
            summary = "Delete a printer",
            description = "Permanently removes a 3D printer from the system."
    )
    @DeleteMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> delete(@RequestParam String id){
        return ResponseEntity.ok(printerService.delete(id));
    }
    @Operation(
            summary = "Mark a printer as unavailable",
            description = "Updates the status of a 3D printer to indicate it is currently out of service or unavailable."
    )
    @PatchMapping("/unavailable")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> unavailable(@RequestParam String id){
        return ResponseEntity.ok(printerService.unavailable(id));
    }
    @Operation(
            summary = "Update an existing printer",
            description = "Updates the details of a specific 3D printer by its unique ID."
    )
    @PutMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody POSTPrinterDTO printerDTO){
        return ResponseEntity.ok(printerService.update(printerDTO, id));
    }
}

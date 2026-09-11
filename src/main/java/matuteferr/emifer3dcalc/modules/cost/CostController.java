package matuteferr.emifer3dcalc.modules.cost;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import matuteferr.emifer3dcalc.config.ErrorResponse;
import matuteferr.emifer3dcalc.exceptions.AdditionalCostException;
import matuteferr.emifer3dcalc.exceptions.DurationException;
import matuteferr.emifer3dcalc.exceptions.FilamentException;
import matuteferr.emifer3dcalc.exceptions.PrinterNotFoundException;
import matuteferr.emifer3dcalc.models.cost.dtos.GETOnlyCostDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostConfigDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostDTO;
import matuteferr.emifer3dcalc.models.printer.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cost")
@Tag(name = "Cost")
public class CostController {

    @Autowired
    private CostService costService;

    @Operation(
            summary = "Calculate final 3D print cost",
            description = "Calculates the total cost for a 3D print job including filament usage, printer power consumption, wear and tear, and profit margin."
    )
    @PostMapping("/calculate")
    public ResponseEntity<GETOnlyCostDTO> calculate(@RequestBody POSTCostDTO postCostDTO) {
        return ResponseEntity.ok(costService.calcFinalCost(postCostDTO));
    }

    @Operation(
            summary = "Update cost configuration",
            description = "Updates the global cost parameters used for calculations, such as profit percentage."
    )
    @PutMapping("/config/update")
    public ResponseEntity<String> update(@RequestBody POSTCostConfigDTO postCostDTO) {
        return ResponseEntity.ok(costService.updateConfig(postCostDTO));
    }

    @Operation(
            summary = "Get cost configuration",
            description = "Retrieves the currently saved global cost configuration settings."
    )
    @GetMapping("/config/get")
    public ResponseEntity<POSTCostConfigDTO> get() {
        return ResponseEntity.ok(costService.getConfig());
    }

    @ExceptionHandler(value = PrinterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePrinterNotFound(PrinterNotFoundException ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = FilamentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleFilamentException(FilamentException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
    @ExceptionHandler(value = AdditionalCostException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAdditionalCost(AdditionalCostException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
    @ExceptionHandler(value = DurationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDurationException(DurationException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}
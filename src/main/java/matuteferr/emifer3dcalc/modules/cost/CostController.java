package matuteferr.emifer3dcalc.modules.cost;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import matuteferr.emifer3dcalc.models.cost.dtos.GETOnlyCostDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostConfigDTO;
import matuteferr.emifer3dcalc.models.cost.dtos.POSTCostDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
}
package matuteferr.emifer3dcalc.modules.cost;

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
    @PostMapping("/calculate")
    public ResponseEntity<GETOnlyCostDTO> calculate(@RequestBody POSTCostDTO postCostDTO){
        return ResponseEntity.ok(costService.calcFinalCost(postCostDTO));
    }
    @PutMapping("/config/update")
    public ResponseEntity<String> update(@RequestBody POSTCostConfigDTO postCostDTO){
        return ResponseEntity.ok(costService.updateConfig(postCostDTO));
    }
    @GetMapping("/config/get")
    public ResponseEntity<POSTCostConfigDTO> get(){
        return ResponseEntity.ok(costService.getConfig());
    }
}

package matuteferr.emifer3dcalc.modules.filament;

import io.swagger.v3.oas.annotations.tags.Tag;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.GETFilamentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filament")
@Tag(name = "Filament", description = "Filament manager")
public class FilamentController {

    @Autowired
    private FilamentService filamentService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody FilamentDTO filamentDTO){
        return ResponseEntity.ok(filamentService.register(filamentDTO));
    }
    @GetMapping("/get")
    public ResponseEntity<List<GETFilamentDTO>> getForStatus(@RequestParam boolean status){
        return ResponseEntity.ok(filamentService.getForStatus(status));
    }
}

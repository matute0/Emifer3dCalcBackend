package matuteferr.emifer3dcalc.modules.filament;

import io.swagger.v3.oas.annotations.tags.Tag;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

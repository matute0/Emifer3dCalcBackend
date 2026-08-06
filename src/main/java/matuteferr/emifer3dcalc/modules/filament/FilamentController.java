package matuteferr.emifer3dcalc.modules.filament;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(
            summary = "Register a new filament",
            description = "Creates and stores a new filament record in the system using the data provided in the request body."
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody FilamentDTO filamentDTO){
        return ResponseEntity.ok(filamentService.register(filamentDTO));
    }
    @Operation(
            summary = "Retrieve filaments list",
            description = "Retrieves a list of registered filaments from the database, allowing optional filtering via query parameters."
    )
    @GetMapping("/get")
    public ResponseEntity<List<GETFilamentDTO>> getForStatus(@RequestParam boolean status){
        return ResponseEntity.ok(filamentService.getForStatus(status));
    }
    @Operation(
            summary = "Get filament ID",
            description = "Searches for and returns the unique identifier of a filament based on the provided parameters."
    )
    @GetMapping("/getID")
    public ResponseEntity<String> getID(@RequestParam String colour, @RequestParam String type, @RequestParam String manufacturer){
        return ResponseEntity.ok(filamentService.getID(colour, type, manufacturer));
    }
    @Operation(
            summary = "Delete a filament",
            description = "Permanently removes or disables a filament from the system based on the specified parameters or identifier."
    )
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody FilamentDTO filamentDTO){
        return ResponseEntity.ok(filamentService.delete(filamentDTO));
    }
    @Operation(
            summary = "Partially update a filament",
            description = "Modifies one or more specific fields of an existing filament identified by its unique ID."
    )
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody FilamentDTO filamentDTO, @PathVariable String id){
        return ResponseEntity.ok(filamentService.update(id, filamentDTO));
    }
}

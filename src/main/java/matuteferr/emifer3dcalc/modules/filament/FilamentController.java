package matuteferr.emifer3dcalc.modules.filament;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import matuteferr.emifer3dcalc.config.ErrorResponse;
import matuteferr.emifer3dcalc.exceptions.*;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.GETFilamentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> register(@RequestBody FilamentDTO filamentDTO){
        return ResponseEntity.ok(filamentService.register(filamentDTO));
    }
    @Operation(
            summary = "Retrieve filaments list",
            description = "Retrieves a list of registered filaments from the database, allowing optional filtering via query parameters."
    )
    @GetMapping("/get")
    public ResponseEntity<?> getForStatus(@RequestParam(defaultValue = "true") boolean status){
        return ResponseEntity.ok(filamentService.getForStatus(status));
    }
    @GetMapping("/getByID")
    public ResponseEntity<?> getByID(@RequestParam String id){
        return ResponseEntity.ok(filamentService.getByID(id));
    }
    @Operation(
            summary = "Delete a filament",
            description = "Permanently removes or disables a filament from the system based on the specified parameters or identifier."
    )
    @DeleteMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> delete(@RequestParam String id){
        return ResponseEntity.ok(filamentService.delete(id));
    }
    @Operation(
            summary = "Partially update a filament",
            description = "Modifies one or more specific fields of an existing filament identified by its unique ID."
    )
    @PatchMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> update(@RequestBody FilamentDTO filamentDTO, @PathVariable String id){
        return ResponseEntity.ok(filamentService.update(id, filamentDTO));
    }

    @ExceptionHandler(value = ColourFormatException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleColourFormatException(ColourFormatException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
    @ExceptionHandler(value = TypeFormatException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleTypeFormatException(TypeFormatException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
    @ExceptionHandler(value = ManufacturerFormatException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleManufacturerFormatException(ManufacturerFormatException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
    @ExceptionHandler(value = AlreadyExistFilamentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAlreadyExistException(AlreadyExistFilamentException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
    @ExceptionHandler(value = FilamentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleFilamentException(FilamentException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}

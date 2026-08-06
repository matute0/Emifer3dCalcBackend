package matuteferr.emifer3dcalc.models.filament;

import matuteferr.emifer3dcalc.models.filament.dtos.FilamentDTO;
import org.springframework.stereotype.Component;

@Component
public class FilamentMapper {
    public Filament dtoToFilament(FilamentDTO filamentDTO){
        return Filament.builder()
                .colour(filamentDTO.getColour())
                .type(filamentDTO.getType())
                .manufacturer(filamentDTO.getManufacturer())
                .build();
    }
    public FilamentDTO filamentToDTO(Filament filament){
        return FilamentDTO.builder()
                .colour(filament.getColour())
                .manufacturer(filament.getManufacturer())
                .type(filament.getType())
                .build();
    }
}

package matuteferr.emifer3dcalc.models.filament;

import matuteferr.emifer3dcalc.models.filament.dtos.FilamentAmountDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentCostDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.GETFilamentDTO;
import org.springframework.stereotype.Component;

@Component
public class FilamentMapper {
    public Filament dtoToFilament(FilamentDTO filamentDTO){
        return Filament.builder()
                .colour(filamentDTO.getColour())
                .type(filamentDTO.getType())
                .manufacturer(filamentDTO.getManufacturer())
                .price(filamentDTO.getPrice())
                .build();
    }
    public FilamentDTO filamentToDTO(Filament filament){
        return FilamentDTO.builder()
                .colour(filament.getColour())
                .manufacturer(filament.getManufacturer())
                .type(filament.getType())
                .build();
    }
    public GETFilamentDTO filamentToGet(Filament filament){
        return GETFilamentDTO.builder()
                .id(filament.getId())
                .status(filament.isStatus())
                .colour(filament.getColour())
                .manufacturer(filament.getManufacturer())
                .type(filament.getType())
                .price(filament.getPrice())
                .build();
    }
    public FilamentCostDTO filamentToCost(FilamentAmountDTO filamentAmountDTO){
        return FilamentCostDTO.builder()
                .filamentID(filamentAmountDTO.getFilamentID())
                .amount(filamentAmountDTO.getAmount())
                .build();
    }
}

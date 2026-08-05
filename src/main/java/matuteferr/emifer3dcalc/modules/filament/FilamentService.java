package matuteferr.emifer3dcalc.modules.filament;

import matuteferr.emifer3dcalc.models.filament.Filament;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentDTO;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilamentService {
    @Autowired
    private FilamentRepository filamentRepository;
    @Autowired
    private FilamentMapper filamentMapper;

    public String register(FilamentDTO filamentDTO){
        Filament filament = filamentMapper.dtoToFilament(filamentDTO);
        filamentRepository.save(filament);
        return "Filament registered";
    }
}

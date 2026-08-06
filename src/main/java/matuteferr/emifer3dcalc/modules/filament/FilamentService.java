package matuteferr.emifer3dcalc.modules.filament;

import matuteferr.emifer3dcalc.models.filament.Filament;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentDTO;
import matuteferr.emifer3dcalc.models.filament.FilamentMapper;
import matuteferr.emifer3dcalc.models.filament.dtos.GETFilamentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilamentService {
    @Autowired
    private FilamentRepository filamentRepository;
    @Autowired
    private FilamentMapper filamentMapper;
    @Autowired
    private FilamentValidations filamentValidations;

    public String register(FilamentDTO filamentDTO){
        Filament filament = filamentMapper.dtoToFilament(filamentDTO);
        filamentValidations.validate(filament);
        filament.setStatus(true);
        filamentRepository.save(filament);
        return "Filament registered";
    }
    public List<GETFilamentDTO> getForStatus(boolean status){
        return filamentRepository.findAllByStatus(status);
    }
}

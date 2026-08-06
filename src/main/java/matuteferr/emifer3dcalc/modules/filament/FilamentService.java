package matuteferr.emifer3dcalc.modules.filament;

import matuteferr.emifer3dcalc.exceptions.FilamentNotFoundException;
import matuteferr.emifer3dcalc.models.filament.Filament;
import matuteferr.emifer3dcalc.models.filament.dtos.FilamentDTO;
import matuteferr.emifer3dcalc.models.filament.FilamentMapper;
import matuteferr.emifer3dcalc.models.filament.dtos.GETFilamentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public String getID(String colour, String type, String manufacturer){
        Optional<Filament> filament = filamentRepository.findByColourAndTypeAndManufacturer(colour, type, manufacturer);
        if(filament.isEmpty()){
            throw new FilamentNotFoundException();
        }
        return filament.get().getId();
    }

    public String delete(FilamentDTO filamentDTO){
        Optional<Filament> filament = filamentRepository.findByColourAndTypeAndManufacturer(filamentDTO.getColour(), filamentDTO.getType(), filamentDTO.getManufacturer());
        if(filament.isEmpty()){
            throw new FilamentNotFoundException();
        }
        filament.get().setStatus(false);
        filamentRepository.save(filament.get());
        return "Filament deleted";
    }

    public String update(String id, FilamentDTO filamentDTO){
        Optional<Filament> filament = filamentRepository.findById(id);
        if(filament.isEmpty()){
            throw new FilamentNotFoundException();
        }
        filamentValidations.validate(filamentMapper.dtoToFilament(filamentDTO));
        filament.get().setType(filamentDTO.getType());
        filament.get().setColour(filamentDTO.getColour());
        filament.get().setManufacturer(filamentDTO.getManufacturer());
        filamentRepository.save(filament.get());
        return "Filament updated";
    }

}

package matuteferr.emifer3dcalc.modules.filament;

import matuteferr.emifer3dcalc.exceptions.AlreadyExistFilamentException;
import matuteferr.emifer3dcalc.exceptions.ColourFormatException;
import matuteferr.emifer3dcalc.exceptions.ManufacturerFormatException;
import matuteferr.emifer3dcalc.exceptions.TypeFormatException;
import matuteferr.emifer3dcalc.models.filament.Filament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilamentValidations {
    @Autowired
    private FilamentRepository filamentRepository;

    public boolean validateColour(String colour){
        return colour.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$");
    }
    public boolean validateType(String type){
        return type.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9\\s+-]+$");
    }
    public boolean validateManufacturer(String manufacturer){
        return manufacturer.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9\\s.\\-&]+$");
    }

    public boolean validateAlreadyExists(Filament filament){
        return filamentRepository.existsByColourAndTypeAndManufacturer(filament.getColour(), filament.getType(), filament.getManufacturer());
    }

    public void validate(Filament filament){
        if(!validateColour(filament.getColour()) || filament.getColour() == null || filament.getColour().trim().isEmpty()){
            throw new ColourFormatException();
        }
        if(!validateType(filament.getType()) || filament.getType() == null || filament.getType().trim().isEmpty()){
            throw new TypeFormatException();
        }
        if(!validateManufacturer(filament.getManufacturer()) || filament.getManufacturer() == null || filament.getManufacturer().trim().isEmpty()){
            throw new ManufacturerFormatException();
        }
        if(validateAlreadyExists(filament)){
            throw new AlreadyExistFilamentException();
        }
    }
}

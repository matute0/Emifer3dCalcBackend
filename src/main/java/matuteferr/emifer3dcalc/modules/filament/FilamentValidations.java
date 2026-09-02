package matuteferr.emifer3dcalc.modules.filament;

import matuteferr.emifer3dcalc.exceptions.*;
import matuteferr.emifer3dcalc.models.filament.Filament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilamentValidations {
    @Autowired
    private FilamentRepository filamentRepository;

    private boolean validateColour(String colour){
        return colour.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$");
    }
    private boolean validateType(String type){
        return type.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9\\s+-]+$");
    }
    private boolean validateManufacturer(String manufacturer){
        return manufacturer.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9\\s.\\-&]+$");
    }
    private boolean validatePrice(int price){
        return price > 0;
    }

    private boolean validateAlreadyExists(Filament filament){
        return filamentRepository.existsByColourAndTypeAndManufacturer(filament.getColour(), filament.getType(), filament.getManufacturer());
    }

    public void validate(Filament filament){
        if(!validateColour(filament.getColour()) || filament.getColour().trim().isEmpty()){
            throw new ColourFormatException();
        }
        if(!validateType(filament.getType()) || filament.getType().trim().isEmpty()){
            throw new TypeFormatException();
        }
        if(!validateManufacturer(filament.getManufacturer()) || filament.getManufacturer().trim().isEmpty()){
            throw new ManufacturerFormatException();
        }
        if(validateAlreadyExists(filament)){
            throw new AlreadyExistFilamentException();
        }
        if(!validatePrice(filament.getPrice())){
            throw new FilamentException();
        }
    }
}

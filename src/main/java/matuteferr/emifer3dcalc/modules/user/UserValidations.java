package matuteferr.emifer3dcalc.modules.user;

import matuteferr.emifer3dcalc.exceptions.*;
import matuteferr.emifer3dcalc.models.user.dtos.POSTUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidations {

    @Autowired
    private UserRepository userRepository;

    public boolean formatValidateUsername(String username){
        return username.matches("^(?=.*\\d)[a-zA-Z0-9_-]{4,32}$");
        // Al menos un numero, permite letras mayusculas, minusculas, numeros, guiones bajos, y guiones, de 4 a 32 caracteres
    }
    public boolean formatValidateEmail(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
    public boolean formatValidatePassword(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[a-zA-Z\\d@$!%*?&._-]{8,64}$");
        // Al menos una letra minuscula, mayuscula, un numero, caracter especial y longitud entre 8 y 64 caracteres
    }
    public boolean alreadyExistUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean alreadyExistEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void validate(POSTUserDTO user){
        if(!formatValidateUsername(user.getUsername())){
            throw new InvalidUsernameException();
        } else
        if(!formatValidateEmail(user.getEmail())){
            throw new EmailFormatException();
        }else
        if(!formatValidatePassword(user.getPassword())){
            throw new PasswordFormatException();
        } else if(alreadyExistEmail(user.getEmail())){
            throw new AlreadyExistEmailException();
        } else if(alreadyExistUsername(user.getUsername())){
            throw new AlreadyExistUsernameException();
        }

    }
}

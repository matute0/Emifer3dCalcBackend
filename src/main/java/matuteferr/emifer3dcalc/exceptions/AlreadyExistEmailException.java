package matuteferr.emifer3dcalc.exceptions;

public class AlreadyExistEmailException extends RuntimeException {
    public AlreadyExistEmailException() {
        super("Este correo ya existe");
    }
}

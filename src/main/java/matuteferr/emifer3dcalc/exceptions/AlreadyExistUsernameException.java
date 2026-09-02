package matuteferr.emifer3dcalc.exceptions;

public class AlreadyExistUsernameException extends RuntimeException {
    public AlreadyExistUsernameException() {
        super("Este nombre de usuario ya existe");
    }
}

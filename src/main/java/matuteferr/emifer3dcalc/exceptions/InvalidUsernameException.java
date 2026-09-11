package matuteferr.emifer3dcalc.exceptions;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("Formato de nombre de usuario inválido");
    }
}

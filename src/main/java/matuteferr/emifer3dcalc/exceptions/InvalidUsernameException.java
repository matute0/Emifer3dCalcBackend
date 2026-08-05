package matuteferr.emifer3dcalc.exceptions;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("Incorrect username format");
    }
}

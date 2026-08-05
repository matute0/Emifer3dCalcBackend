package matuteferr.emifer3dcalc.exceptions;

public class AlreadyExistUsernameException extends RuntimeException {
    public AlreadyExistUsernameException() {
        super("This username already exist");
    }
}

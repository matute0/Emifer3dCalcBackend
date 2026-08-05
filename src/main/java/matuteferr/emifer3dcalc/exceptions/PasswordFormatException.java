package matuteferr.emifer3dcalc.exceptions;

public class PasswordFormatException extends RuntimeException {
    public PasswordFormatException() {
        super("Incorrect Password Format");
    }
}

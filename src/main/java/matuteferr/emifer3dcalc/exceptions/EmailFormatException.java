package matuteferr.emifer3dcalc.exceptions;

public class EmailFormatException extends RuntimeException {
    public EmailFormatException() {
        super("Incorrect email format");
    }
}

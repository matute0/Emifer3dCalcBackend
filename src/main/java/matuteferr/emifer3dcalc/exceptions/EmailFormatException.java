package matuteferr.emifer3dcalc.exceptions;

public class EmailFormatException extends RuntimeException {
    public EmailFormatException() {
        super("Formato de correo inválido");
    }
}

package matuteferr.emifer3dcalc.exceptions;

public class PasswordFormatException extends RuntimeException {
    public PasswordFormatException() {
        super("Formato de contraseña inválido");
    }
}

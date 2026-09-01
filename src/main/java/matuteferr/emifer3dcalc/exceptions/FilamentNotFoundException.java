package matuteferr.emifer3dcalc.exceptions;

public class FilamentNotFoundException extends RuntimeException {
    public FilamentNotFoundException() {
        super("Filamento no encontrado");
    }
}

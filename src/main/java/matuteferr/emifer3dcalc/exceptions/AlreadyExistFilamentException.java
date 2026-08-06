package matuteferr.emifer3dcalc.exceptions;

public class AlreadyExistFilamentException extends RuntimeException {
    public AlreadyExistFilamentException() {
        super("This filament already exist");
    }
}

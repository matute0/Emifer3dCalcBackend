package matuteferr.emifer3dcalc.exceptions;

public class AlreadyExistFilamentException extends RuntimeException {
    public AlreadyExistFilamentException() {
        super("Este filamento ya existe");
    }
}

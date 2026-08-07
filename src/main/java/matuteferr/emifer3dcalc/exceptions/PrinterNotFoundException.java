package matuteferr.emifer3dcalc.exceptions;

public class PrinterNotFoundException extends RuntimeException {
    public PrinterNotFoundException() {
        super("Printer not found");
    }
}

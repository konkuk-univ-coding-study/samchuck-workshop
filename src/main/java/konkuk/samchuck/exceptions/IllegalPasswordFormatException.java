package konkuk.samchuck.exceptions;

public class IllegalPasswordFormatException extends IllegalArgumentException {
    public IllegalPasswordFormatException() {
    }

    public IllegalPasswordFormatException(String s) {
        super(s);
    }

    public IllegalPasswordFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPasswordFormatException(Throwable cause) {
        super(cause);
    }
}

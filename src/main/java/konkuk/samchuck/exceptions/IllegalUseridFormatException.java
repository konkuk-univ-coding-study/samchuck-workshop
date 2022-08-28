package konkuk.samchuck.exceptions;

public class IllegalUseridFormatException extends IllegalArgumentException {

    public IllegalUseridFormatException() {
    }

    public IllegalUseridFormatException(String s) {
        super(s);
    }

    public IllegalUseridFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalUseridFormatException(Throwable cause) {
        super(cause);
    }
}

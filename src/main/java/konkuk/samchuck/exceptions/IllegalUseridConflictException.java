package konkuk.samchuck.exceptions;

public class IllegalUseridConflictException extends IllegalArgumentException {
    public IllegalUseridConflictException() {
    }

    public IllegalUseridConflictException(String s) {
        super(s);
    }

    public IllegalUseridConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalUseridConflictException(Throwable cause) {
        super(cause);
    }
}

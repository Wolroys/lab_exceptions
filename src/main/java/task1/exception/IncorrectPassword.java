package task1.exception;

public class IncorrectPassword extends Throwable {
    public IncorrectPassword(String message) {
        super(message);
    }
}

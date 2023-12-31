package task1.exception;

public class AccountIsLockedException extends Throwable {
    public AccountIsLockedException(String message) {
        super(message);
    }
}

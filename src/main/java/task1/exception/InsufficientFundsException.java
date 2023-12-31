package task1.exception;

public class InsufficientFundsException extends CurrencyException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

package task1.interfaces;

import task1.exception.AccountIsLockedException;
import task1.exception.IncorrectPassword;

public interface PinValidator {
    boolean characterHandler(String symbol);
    boolean checkPassword(char[] pin) throws IncorrectPassword;
    boolean login() throws AccountIsLockedException;
}

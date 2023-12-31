package task1.interfaces;

import task1.exception.AccountIsLockedException;

import java.io.IOException;

public interface Terminal {

    void enterPin() throws IOException, AccountIsLockedException;
    void checkBalance();
    void withdraw(int amount);
    void deposit(int amount);
}

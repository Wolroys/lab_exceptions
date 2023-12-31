package task1.interfaces;

import task1.exception.CurrencyException;
import task1.exception.CurrencyIssuanceException;
import task1.exception.InsufficientFundsException;

public interface TerminalServer {
    void deposit(int amount) throws CurrencyIssuanceException;

    void withdraw(int amount) throws CurrencyException;

    int checkBalance();
}

package task1;

import task1.exception.CurrencyException;
import task1.exception.CurrencyIssuanceException;
import task1.exception.InsufficientFundsException;
import task1.interfaces.TerminalServer;

public class TerminalServerImpl implements TerminalServer {

    private int balance;

    @Override
    public void deposit(int amount) throws CurrencyIssuanceException {
        if (amount % 100 != 0)
            throw new CurrencyIssuanceException("It is not possible to accept the deposited currency");

        balance += amount;
    }

    @Override
    public void withdraw(int amount) throws CurrencyException {
        if (amount % 100 != 0)
            throw new CurrencyIssuanceException("It is not possible to accept the deposited currency");
        else if (amount > balance)
            throw new InsufficientFundsException("There are not enough funds in your account");

        balance -= amount;
    }

    @Override
    public int checkBalance() {
        return balance;
    }
}

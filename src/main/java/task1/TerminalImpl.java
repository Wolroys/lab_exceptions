package task1;


import task1.exception.AccountIsLockedException;
import task1.exception.CurrencyException;
import task1.exception.CurrencyIssuanceException;
import task1.interfaces.MessageHandler;
import task1.interfaces.PinValidator;
import task1.interfaces.Terminal;
import task1.interfaces.TerminalServer;

import java.util.Scanner;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator pinValidator;

    private final MessageHandler handler = new ConsoleHandler();

    public TerminalImpl(TerminalServerImpl server, PinValidatorImpl pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public boolean enterPin() {
        try {
            return pinValidator.login();
        } catch (AccountIsLockedException e) {
            handler.show(e.getMessage());
            return false;
        }
    }

    @Override
    public void checkBalance() {
        handler.show("Your balance is - " + server.checkBalance() + "$");
    }

    @Override
    public void withdraw(int amount) {
        try {
            server.withdraw(amount);
            handler.show("Successful operation");
        } catch (CurrencyException e) {
            handler.show(e.getMessage());
        }
    }

    @Override
    public void deposit(int amount) {
        try {
            server.deposit(amount);
            handler.show("Successful operation");
        } catch (CurrencyIssuanceException e) {
            handler.show(e.getMessage());
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        handler.show("Hello, do you want to use terminal (Y/N)?");

        while (!scanner.nextLine().equals("N") && !enterPin()){
            handler.show("Do you want to continue? (Y/N)");
        }

        printUserInterface();

        String choice;

        while (!(choice = scanner.nextLine()).equals("0")){
            int amount = 0;
            switch (choice) {
                case "1" -> {
                    System.out.println("How much money do you want to put into the account?");
                    try {
                        amount = Integer.parseInt(scanner.nextLine());
                        deposit(amount);
                    } catch (NumberFormatException e) {
                        handler.show("You should write a number");
                    }
                }
                case "2" -> {
                    System.out.println("How much money do you want to withdraw from your account?");
                    try {
                        amount = Integer.parseInt(scanner.nextLine());
                        withdraw(amount);
                    } catch (NumberFormatException e) {
                        handler.show("You should write a number");
                    }
                }
                case "3" -> checkBalance();
                case "4" -> printUserInterface();
                default -> handler.show("Unknown command");
            }
        }

        handler.show("Have a good day!");
    }

    private void printUserInterface(){
        handler.show("""
                What kind of operation do you want to perform?
                deposit money - 1
                withdraw money - 2
                check your balance - 3
                show menu - 4
                exit - 0""");
    }
}

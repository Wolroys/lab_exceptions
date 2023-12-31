package task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task1.exception.AccountIsLockedException;
import task1.exception.CurrencyException;
import task1.exception.CurrencyIssuanceException;
import task1.interfaces.PinValidator;
import task1.interfaces.Terminal;
import task1.interfaces.TerminalServer;

import java.util.Scanner;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServerImpl server, PinValidatorImpl pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public boolean enterPin() {
        try {
            return pinValidator.login();
        } catch (AccountIsLockedException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void checkBalance() {
        System.out.println("Your balance is - " + server.checkBalance() + "$");
    }

    @Override
    public void withdraw(int amount) {
        try {
            server.withdraw(amount);
            System.out.println("Successful operation");
        } catch (CurrencyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deposit(int amount) {
        try {
            server.deposit(amount);
            System.out.println("Successful operation");
        } catch (CurrencyIssuanceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, do you want to use terminal (Y/N)?");

        while (!scanner.nextLine().equals("N") && !enterPin()){
            System.out.println("Do you want to continue? (Y/N)");
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
                        System.out.println("You should write a number");
                    }
                }
                case "2" -> {
                    System.out.println("How much money do you want to withdraw from your account?");
                    try {
                        amount = Integer.parseInt(scanner.nextLine());
                        withdraw(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("You should write a number");
                    }
                }
                case "3" -> checkBalance();
                case "4" -> printUserInterface();
                default -> System.out.println("Unknown command");
            }
        }

        System.out.println("Have a good day!");
    }

    private void printUserInterface(){
        System.out.println("""
                What kind of operation do you want to perform?
                deposit money - 1
                withdraw money - 2
                check your balance - 3
                show menu - 4
                exit - 0""");
    }
}

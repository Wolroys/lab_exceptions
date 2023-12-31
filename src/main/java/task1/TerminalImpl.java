package task1;

import task1.exception.AccountIsLockedException;
import task1.exception.CurrencyException;
import task1.exception.CurrencyIssuanceException;
import task1.exception.IncorrectPassword;
import task1.interfaces.PinValidator;
import task1.interfaces.Terminal;
import task1.interfaces.TerminalServer;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator pinValidator;

    private LocalTime coolDown = LocalTime.MIN;
    private boolean isAuth;

    private int countOfMistakes;

    public TerminalImpl(TerminalServerImpl server, PinValidatorImpl pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
        countOfMistakes = 0;
        isAuth = false;
    }

    @Override
    public void enterPin() throws AccountIsLockedException {
        if (isBanned()) {
            throw new AccountIsLockedException(String.valueOf(
                    (coolDown.until(LocalTime.now(), ChronoUnit.SECONDS) + " left")));
        }
        char[] pin = {'_', '_', '_', '_'};
        int i = 0;
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write your pin: ");
            while (i < 4 && countOfMistakes < 3){

                System.out.println(Arrays.toString(pin));
                String digit = scanner.nextLine();

                if (!pinValidator.characterHandler(digit))
                    continue;

                pin[i++] = digit.charAt(0);

                if (i == 4){
                    if (!pinValidator.checkPassword(pin)) {
                        i = 0;
                        countOfMistakes++;
                        Arrays.fill(pin, '_');
                    }
                    isAuth = true;
                }
            }

            if (countOfMistakes == 3){
                coolDown = LocalTime.now();
                System.out.println("You have made too many login attempts. Wait 10 seconds");
            }
        } catch (IncorrectPassword e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void checkBalance() {
        System.out.println("Your balance is - " + server.checkBalance());
    }

    @Override
    public void withdraw(int amount) {
        try {
            server.withdraw(amount);
        } catch (CurrencyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deposit(int amount) {
        try {
            server.deposit(amount);
        } catch (CurrencyIssuanceException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isBanned(){
        if (coolDown.plusSeconds(10).isBefore(LocalTime.now())){
            countOfMistakes = 0;
            return false;
        };
        return true;
    }
}

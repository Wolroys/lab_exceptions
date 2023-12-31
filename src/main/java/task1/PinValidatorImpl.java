package task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task1.exception.AccountIsLockedException;
import task1.exception.IncorrectPassword;
import task1.interfaces.ExceptionHandler;
import task1.interfaces.PinValidator;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;

public class PinValidatorImpl implements PinValidator {
    private final char[] accountPin = {'1', '2', '3', '4'};
    private int countOfMistakes;
    private LocalTime coolDown;
    private final ExceptionHandler handler = new ConsoleExceptionHandler();

    {
        countOfMistakes = 0;
        coolDown = LocalTime.MIN;
    }


    @Override
    public boolean characterHandler(String symbol) {
        try {
            int digit = Integer.parseInt(symbol);
            if (digit < 0 || digit >= 10){
                handler.showException("You should write a digit between 0 and 9");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            handler.showException("You should write a DIGIT between 0 and 9");
            return false;
        }
    }

    @Override
    public boolean checkPassword(char[] pin) throws IncorrectPassword {
         if (Arrays.equals(accountPin, pin)){
             System.out.println("successful login");
             return true;
         }
         throw new IncorrectPassword("Incorrect password");
    }

    @Override
    public boolean login() throws AccountIsLockedException {
        if (isBanned()) {
            throw new AccountIsLockedException((10 - coolDown.until(LocalTime.now(),
                    ChronoUnit.SECONDS)) + " seconds left");
        }
        char[] pin = {'_', '_', '_', '_'};
        int i = 0;
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write your pin: ");
            while (i < 4 && countOfMistakes < 3){

                System.out.println(Arrays.toString(pin));
                String digit = scanner.nextLine();

                if (!characterHandler(digit))
                    continue;

                pin[i++] = digit.charAt(0);

                if (i == 4){
                    if (!checkPassword(pin)) {
                        i = 0;
                        countOfMistakes++;
                        Arrays.fill(pin, '_');
                    }
                }
            }

            if (countOfMistakes == 3){
                coolDown = LocalTime.now();
                System.out.println("You have made too many login attempts. Wait 10 seconds");
            }

            return true;
        } catch (IncorrectPassword e) {
            handler.showException(e.getMessage());
        }

        return false;
    }

    private boolean isBanned(){
        if (coolDown.plusSeconds(10).isBefore(LocalTime.now())){
            countOfMistakes = 0;
            return false;
        };
        return true;
    }

}

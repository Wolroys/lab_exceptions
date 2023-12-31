package task1;

import task1.exception.IncorrectPassword;
import task1.interfaces.PinValidator;

import java.util.Arrays;

public class PinValidatorImpl implements PinValidator {
    private final char[] accountPin = {'1', '2', '3', '4'};
    private int countOfMistakes;

    {
        countOfMistakes = 0;
    }


    @Override
    public boolean characterHandler(String symbol) {
        try {
            int digit = Integer.parseInt(symbol);
            if (digit < 0 || digit >= 10){
                System.out.println("You should write a digit between 0 and 9");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("You should write a DIGIT between 0 and 9");
            return false;
        }
    }

    @Override
    public boolean checkPassword(char[] pin) throws IncorrectPassword {
         if (Arrays.equals(accountPin, pin)){
             System.out.println("successful login");
             return true;
         }
         System.out.println("Incorrect password");
         return false;
    }
}

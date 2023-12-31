package task1;

import task1.exception.AccountIsLockedException;
import task1.interfaces.Terminal;

import java.io.IOException;
import java.util.Scanner;

public class TerminalLaunch {
    public static void main(String[] args) throws AccountIsLockedException, IOException {
        Terminal t = new TerminalImpl(new TerminalServerImpl(), new PinValidatorImpl());
        Scanner scanner = new Scanner(System.in);

        while (!scanner.nextLine().equals("exit")){
            t.enterPin();
            System.out.println("Enter something: ");
        }
    }
}

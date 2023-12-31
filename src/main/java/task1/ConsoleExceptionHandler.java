package task1;

import task1.interfaces.ExceptionHandler;

public class ConsoleExceptionHandler implements ExceptionHandler {
    @Override
    public void showException(String message) {
        System.out.println(message);
    }
}

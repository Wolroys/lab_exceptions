package task1;

import task1.interfaces.MessageHandler;

public class ConsoleHandler implements MessageHandler {
    @Override
    public void show(String message) {
        System.out.println(message);
    }
}

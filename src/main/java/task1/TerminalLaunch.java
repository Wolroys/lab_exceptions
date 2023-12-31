package task1;

import task1.interfaces.Terminal;


public class TerminalLaunch {
    public static void main(String[] args){
        Terminal t = new TerminalImpl(new TerminalServerImpl(), new PinValidatorImpl());
        t.run();
    }
}

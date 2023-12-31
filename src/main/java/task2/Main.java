package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line;
        while (scanner.hasNext() && !(line = scanner.nextLine()).equals("stop")){
            try {
                System.out.println("Specify the url below: ");
                WebReader.readContent(line);
            } catch (IOException e) {
                System.out.println("Try again or enter 'stop'. Put your url below: ");
            }
        }
    }
}

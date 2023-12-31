package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class WebReader {

    public static void readContent(String webUrl) throws IOException {
        URL url = new URL(webUrl);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
    }
}

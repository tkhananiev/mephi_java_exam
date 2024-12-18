package mephi.java.exam;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;


public class Main {
    public static void main (String[] args) throws URISyntaxException, UnsupportedOperationException, IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите url: ");
        String inputUrl = scanner.nextLine();
        String url = ShortUrl.getShortUrl(inputUrl);
        System.out.println(url);
        Desktop.getDesktop().browse(new URI(url));

    }
}
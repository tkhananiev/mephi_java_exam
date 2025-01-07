package mephi.java.exam;
import mephi.java.exam.command.CommandProcessor;
import mephi.java.exam.service.LinkService;
import mephi.java.exam.service.UserService;

import java.io.IOException;
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;


public class Main {
    public static void main (String[] args) {

        System.out.println("Добрый день, пользователь!");
        UserService userService = new UserService();
        LinkService linkService = new LinkService();
        CommandProcessor commandProcessor = new CommandProcessor(userService, linkService);
        try(Scanner scanner = new Scanner(System.in)){
            while(true){
                System.out.print("Введите команду: ");
                String line = scanner.nextLine();
                boolean continueWork = commandProcessor.process(line);
                if(!continueWork){
                    break;
                }
            }

        }

        System.out.println("Программа завершена!");

    }
}
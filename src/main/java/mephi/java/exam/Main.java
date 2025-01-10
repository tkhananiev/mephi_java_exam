package mephi.java.exam;
import mephi.java.exam.command.CommandProcessor;
import mephi.java.exam.service.LinkService;
import mephi.java.exam.service.UserService;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        System.out.println("Добрый день, пользователь!");
        UserService userService = new UserService();
        LinkService linkService = new LinkService();
        CommandProcessor commandProcessor = new CommandProcessor(userService, linkService);
        try(Scanner scanner = new Scanner(System.in)){
            while(true){
                printMenu();
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
    private static void printMenu(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Доступные команды: ");
        System.out.println("Создание новой ссылки и нового пользователя: create <originalUrl> <limit>");
        System.out.println("Создание новой ссылки для существующего пользователя: create <uuid> <originalUrl> <limit>");
        System.out.println("Редактирование ссылки: edit <uuid> <shortUrl> <newOriginalUrl>");
        System.out.println("Удаление ссылки: remove <uuid> <shortUrl>");
        System.out.println("Переход в браузере по ссылке: goto <shortUrl>");
        System.out.println("Список ссылок пользователя: list <uuid>");
        System.out.println("Завершение программы: exit");
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
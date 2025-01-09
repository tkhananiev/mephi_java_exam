package mephi.java.exam.command;

import mephi.java.exam.model.Link;
import mephi.java.exam.model.User;
import mephi.java.exam.service.LinkService;
import mephi.java.exam.service.UserService;

public class CreateCommand implements Command{
    private final UserService userService;
    private final LinkService linkService;

    public CreateCommand(UserService userService, LinkService linkService) {
        this.userService = userService;
        this.linkService = linkService;
    }

    @Override
    public void execute(String[] args){
        if (args.length == 3){
            String originalUrl = args[1];
            Integer limit = parseLimit(args[2]);
            String uuid = userService.createUser();
            User user = userService.getUserByUuid(uuid);
            System.out.println("Ваш новый UUID " + uuid);
            Link link = linkService.createLink(user, originalUrl, limit);
            System.out.println("Ссылка создана: " + link.getShortUrl());


        } else if (args.length == 4){
            String uuid = args[1];
            String originalUrl = args[2];
            Integer limit = parseLimit(args[3]);
            User user = userService.getUserByUuid(uuid);
            if (user == null){
                System.out.println("Пользователь с подобным uuid не найден. Создаем нового пользователя");
                uuid = userService.createUser();
                user = userService.getUserByUuid(uuid);
                System.out.println("Ваш новый UUID " + uuid);
            }
            Link link = linkService.createLink(user, originalUrl, limit);
            System.out.println("Ссылка создана: " + link.getShortUrl());
        } else {
            System.out.println("Неверный формат команды. Примеры ввода команд:");
            System.out.println("   create <originalUrl> <limit>");
            System.out.println("или ");
            System.out.println("    create <uuid> <originalUrl> <limit>");
        }

    }

    private Integer parseLimit(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException exception){
            return null;
        }
    }

}

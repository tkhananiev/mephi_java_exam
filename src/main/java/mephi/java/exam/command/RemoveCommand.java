package mephi.java.exam.command;

import mephi.java.exam.model.Link;
import mephi.java.exam.model.User;
import mephi.java.exam.service.LinkService;
import mephi.java.exam.service.UserService;

public class RemoveCommand implements Command {

    private final UserService userService;
    private final LinkService linkService;

    public RemoveCommand(UserService userService, LinkService linkService) {
        this.userService = userService;
        this.linkService = linkService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3){
            System.out.println("Неверный формат команды. Пример верного формата: ");
            System.out.println("    remove <uuid> <shortUrl>");
            return;
        }
        String uuid = args[1];
        String shortUrl = args[2];
        Link link = linkService.getLinkByShortUrl(shortUrl);
        User user = userService.getUserByUuid(uuid);
        if (user == null){
            System.out.println("Пользователь не найден. Удаление ссылки невозможно.");
            return;
        }
        boolean removeUrl = linkService.removeLink(link, user);
        if (!removeUrl){
            System.out.println("Ссылка успешно удалена!");
        } else {
            System.out.println("Удаление невозможно. Либо ссылка не найдена, либо Вы не ее владелец.");
        }

    }
}

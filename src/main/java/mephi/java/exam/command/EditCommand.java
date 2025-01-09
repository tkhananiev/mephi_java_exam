package mephi.java.exam.command;

import mephi.java.exam.model.User;
import mephi.java.exam.service.LinkService;
import mephi.java.exam.service.UserService;

public class EditCommand implements Command{

    private final UserService userService;
    private final LinkService linkService;

    public EditCommand(UserService userService, LinkService linkService) {
        this.userService = userService;
        this.linkService = linkService;
    }

    @Override
    public void execute(String[] args){
        if (args.length != 4){
            System.out.println("Неверный формат команды. Пример верного формата: ");
            System.out.println("    edit <uuid> <shortUrl> <newOriginalUrl>");
            return;
        }
        String uuid = args[1];
        String shortUrl = args[2];
        String newOriginalUrl = args[3];
        User user = userService.getUserByUuid(uuid);
        if (user == null){
            System.out.println("Пользователь не найден. Редактирование ссылки невозможно.");
            return;
        }
        boolean updatedLink = linkService.updateLink(shortUrl, newOriginalUrl, uuid);
        if (updatedLink){
            System.out.println("Ссылка успешно обновлена!");
        } else {
            System.out.println("Редактирование невозможно. Либо ссылка не найдена, либо Вы не ее владелец.");
        }
    }
}

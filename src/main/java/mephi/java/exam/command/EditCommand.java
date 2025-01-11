package mephi.java.exam.command;

import mephi.java.exam.model.Link;
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
            System.out.println("    edit <uuid> <shortUrl> <newClickLimit>");
            return;
        }
        String uuid = args[1];
        String shortUrl = args[2];
        User user = userService.getUserByUuid(uuid);
        if (user == null){
            System.out.println("Пользователь не найден. Обновление лимитиа перехода по ссылке невозможно.");
            return;
        }
        Link link = linkService.getLinkByShortUrl(shortUrl);
        if (link == null){
            System.out.println("Ссылка не найдена.");
            return;
        }
        int newClickLimit = parseLimit(args[3], link.getMaxClicks());

        boolean updatedLink = linkService.updateLink(shortUrl, newClickLimit, uuid);
        if (updatedLink){
            System.out.println("Лимит перехода успешно обновлен!");
        } else {
            System.out.println("Обновление лимита перехода невозможно. Либо ссылка не найдена, либо Вы не ее владелец.");
        }
    }
    private Integer parseLimit(String arg, int defaultClickLimit) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException exception){
            return defaultClickLimit;
        }
    }
}

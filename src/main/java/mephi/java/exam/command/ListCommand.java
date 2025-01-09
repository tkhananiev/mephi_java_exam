package mephi.java.exam.command;

import mephi.java.exam.model.Link;
import mephi.java.exam.model.User;
import mephi.java.exam.service.UserService;

import java.util.SimpleTimeZone;

public class ListCommand implements Command{
    private final UserService userService;

    public ListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(String[] args){
        if (args.length != 2){
            System.out.println("Неверный формат ввода. Пример: list <uuid>");
            return;
        }
        String uuid = args[1];
        User user = userService.getUserByUuid(uuid);
        if (user == null){
            System.out.println("Пользователь не найден.");
            return;
        }
        System.out.println("Ссылки пользователя " + uuid + ":");
        for(Link link : user.getLinks()){
            String expiredText = link.isExpired()? "Да" : "Нет";
            String limitClicksText = link.isLimitClicks()? "Да" : "Нет";
            System.out.println( link.getShortUrl() + ">" + link.getOriginalUrl()
                    + " | Протухла? " + expiredText  + "| Лимит достигнут? " + limitClicksText);
        }
    }
}

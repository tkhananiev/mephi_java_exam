package mephi.java.exam.command;

import mephi.java.exam.model.Link;
import mephi.java.exam.model.User;
import mephi.java.exam.service.LinkService;
import mephi.java.exam.service.UserService;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public class GoToCommand implements Command {
    private final UserService userService;
    private final LinkService linkService;

    public GoToCommand(UserService userService, LinkService linkService) {
        this.userService = userService;
        this.linkService = linkService;
    }

    @Override
    public void execute(String[] args){
        if (args.length != 2){
            System.out.println("Неверный формат ввода. Верный формат: goto <http://short.lenta.ru/abc123>");
            return;
        }
        String shortUrl = args[1];
        Link link = linkService.getLinkByShortUrl(shortUrl);
        if (link == null){
            System.out.println("Ссылка не найдена.");
            return;
        }
        if (link.isExpired()){
            System.out.println("Истек срок дествия ссылки.");
            User user = userService.getUserByUuid(link.getOwnerUuid());
            linkService.removeLink(link, user);
            return;
        }
        if (link.isLimitClicks()){
            System.out.println("Лимит переходов по ссылке исчерпан.");
            return;
        }
        link.incrementClicks();
        openInBrowser(link.getOriginalUrl());
    }
    private void openInBrowser(String url){
        try {
            if (!(url.startsWith("http://") || url.startsWith("https://"))){
                url = "http://" + url;
            }
            Desktop.getDesktop().browse(new URI(url));
            System.out.println("Открываем " + url);
        } catch (Exception e) {
            System.out.println("Не удалось открыть ссылку в браузере : " + e.getMessage());
        }
    }
}

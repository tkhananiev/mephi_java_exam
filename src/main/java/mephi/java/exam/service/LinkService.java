package mephi.java.exam.service;

import mephi.java.exam.config.ConfigManager;
import mephi.java.exam.model.Link;
import mephi.java.exam.model.User;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class LinkService {
    private static final String BASE_URL = "http://short.lenta.ru/";
    private static final int SHORT_CODE_LENGTH = 7;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final SecureRandom random = new SecureRandom();
    private final Map<String, Link> linkMap = new HashMap<>();

    public Link createLink(User user, String originalUrl, Integer requestLimit){
        String shortUrl = BASE_URL+generateShortCode();
        int limit = (requestLimit == null || requestLimit <= 0) ? ConfigManager.getClicksLimit(): requestLimit;
        //Link Creation String originalUrl, String shortUrl, String ownerUuid, long lifeTimeSeconds, long maxClicks
        Link link = new Link(originalUrl, shortUrl, user.getUuid(), ConfigManager.getLinkLifeTimeSeconds(), limit);
        linkMap.put(shortUrl, link);
        user.addLink(link);
        return link;
    }
    //генерация короткой ссылки
    private String generateShortCode() {

        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int idx = random.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(idx));
        }
        return sb.toString();
    }

    public void removeLink(Link link, User user){
        linkMap.remove(link.getShortUrl());
        user.removeLink(link);
    }

    public boolean updateLink(String shortUrl, String originalUrl, String userUuid){
        Link link = linkMap.get(shortUrl);
        if (link == null){
            return false;
        }
        if (!link.getOwnerUuid().equals(userUuid)){
            return false;
        }
        link.setOriginalUrl(originalUrl);
        return true;
    }
}

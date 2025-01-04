package mephi.java.exam.service;

import mephi.java.exam.config.ConfigManager;
import mephi.java.exam.model.Link;
import mephi.java.exam.model.User;

import java.util.HashMap;
import java.util.Map;

public class LinkService {
    private static final String BASE_URL = "http://short.lenta.ru/";
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

    private String generateShortCode() {
        return null;
    }

}

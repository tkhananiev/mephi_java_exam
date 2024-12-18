package mephi.java.exam.model;


import java.util.ArrayList;
import java.util.List;

public class User {
    private final String uuid;
    private final List<Link> links;

    public User(String uuid) {
        this.uuid = uuid;
        this.links = new ArrayList<>();
    }


    public String getUuid() {
        return uuid;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void addLink(Link link) {
        links.add(link);
    }
    public void removeLink(Link link){
        links.remove(link);
    }
}

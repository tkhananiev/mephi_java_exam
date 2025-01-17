package mephi.java.exam.model;

import java.time.Instant;

public class Link {
    private String originalUrl;
    private final String shortUrl;
    private final String ownerUuid;
    private final Instant creationTime;
    private final long lifeTimeSeconds;
    private int maxClicks;
    private int currentClicks;

    public Link(String originalUrl, String shortUrl, String ownerUuid, long lifeTimeSeconds, int maxClicks) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.ownerUuid = ownerUuid;
        this.lifeTimeSeconds = lifeTimeSeconds;
        this.maxClicks = maxClicks;
        this.currentClicks = 0;
        this.creationTime = Instant.now();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getOwnerUuid() {
        return ownerUuid;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public long getLifeTimeSeconds() {
        return lifeTimeSeconds;
    }

    public int getMaxClicks() {
        return maxClicks;
    }

    public void setMaxClicks(int maxClicks) {
        this.maxClicks = maxClicks;
    }

    public long getCurrentClicks() {
        return currentClicks;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setCurrentClicks(int currentClicks) {
        this.currentClicks = currentClicks;
    }

    public boolean isExpired(){
        return Instant.now().isAfter(creationTime.plusSeconds(lifeTimeSeconds));
    }

    public boolean isLimitClicks(){
        return currentClicks >= maxClicks;
    }

    public void incrementClicks(){
        currentClicks++;
    }
}

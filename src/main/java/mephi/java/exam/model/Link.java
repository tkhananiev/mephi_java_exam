package mephi.java.exam.model;

import java.time.Instant;

public class Link {
    private String originalUrl;
    private final String shortUrl;
    private final String ownerUuid;
    private final Instant creationTime;
    private final long lifeTimeSeconds;
    private final long maxClicks;
    private long currentClicks;

    public Link(String originalUrl, String shortUrl, String ownerUuid, long lifeTimeSeconds, long maxClicks) {
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

    public long getMaxClicks() {
        return maxClicks;
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

    public void setCurrentClicks(long currentClicks) {
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

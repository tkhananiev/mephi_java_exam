package mephi.java.exam.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final long DEFAULT_LINK_LIFETIME = 3600;
    private static final long DEFAULT_LINK_LIMIT = 10;
    private static long linkLifeTimeSeconds;
    private static long clicksLimit;

    private ConfigManager() {
    }

    static{
        Properties properties = new Properties();
        try(InputStream is = ConfigManager.class.getResourceAsStream("resources/config.properties")){
            if (is != null){
                properties.load(is);
            }

            linkLifeTimeSeconds = Long.parseLong(properties.getProperty("link.lifetime", String.valueOf(DEFAULT_LINK_LIFETIME)));
            clicksLimit = Long.parseLong(properties.getProperty("link.limit", String.valueOf(DEFAULT_LINK_LIFETIME)));

        } catch (Exception e) {
            linkLifeTimeSeconds = DEFAULT_LINK_LIFETIME;
            clicksLimit = DEFAULT_LINK_LIMIT;
        }

    }

    public static long getClicksLimit() {
        return clicksLimit;
    }

    public static long getLinkLifeTimeSeconds() {
        return linkLifeTimeSeconds;
    }
}

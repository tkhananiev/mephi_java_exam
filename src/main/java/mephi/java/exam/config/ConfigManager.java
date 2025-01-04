package mephi.java.exam.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final long DEFAULT_LINK_LIFETIME = 3600;
    private static final int DEFAULT_LINK_LIMIT = 10;
    private static long linkLifeTimeSeconds;
    private static int clicksLimit;

    private ConfigManager() {
    }

    static{
        Properties properties = new Properties();
        try(InputStream is = ConfigManager.class.getResourceAsStream("/config.properties")){
            if (is != null){
                properties.load(is);
            }

            linkLifeTimeSeconds = Long.parseLong(properties.getProperty("link.lifetime", String.valueOf(DEFAULT_LINK_LIFETIME)));
            clicksLimit = Integer.parseInt(properties.getProperty("link.limit", String.valueOf(DEFAULT_LINK_LIFETIME)));

        } catch (Exception e) {
            linkLifeTimeSeconds = DEFAULT_LINK_LIFETIME;
            clicksLimit = DEFAULT_LINK_LIMIT;
        }

    }

    public static int getClicksLimit() {
        return clicksLimit;
    }

    public static long getLinkLifeTimeSeconds() {
        return linkLifeTimeSeconds;
    }
}

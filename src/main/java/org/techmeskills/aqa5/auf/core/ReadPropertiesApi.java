package org.techmeskills.aqa5.auf.core;

import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesApi {
    protected Properties properties;

    public ReadPropertiesApi() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("restapi.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getURL() {
        return properties.getProperty("url");
    }

    public String getBrowserName() {
        return properties.getProperty(  "browser");
    }

    public boolean isHeadless() {
        return properties.getProperty("headless").equalsIgnoreCase("true");
    }

    public int getTimeOut() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}

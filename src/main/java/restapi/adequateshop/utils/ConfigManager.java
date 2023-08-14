package restapi.adequateshop.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager manager;
    private static Properties prop;

    private ConfigManager() throws IOException {
        if(prop == null) {
            prop = new Properties();
        }
        prop.load(new FileInputStream("src/main/resources/config.properties"));
    }

    public static ConfigManager getInstance() {
        if (manager == null) {
            synchronized (ConfigManager.class) {
                try {
                    manager = new ConfigManager();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return manager;
    }

    public String getString(String key) {
        return System.getProperty(key, prop.getProperty(key));
    }
}

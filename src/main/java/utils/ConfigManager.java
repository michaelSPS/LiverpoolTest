package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    // Método reutilizable para cargar cualquier archivo .properties
    public static String getProperty(String key, String filePath) throws IOException {
        FileReader fr = new FileReader(filePath);
        Properties pr = new Properties();
        pr.load(fr);
        String value = pr.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("❌ Key '" + key + "' not found in file: " + filePath);
        }
        return value;
    }

    // Acceso específico a config.properties
    public static String getConfig(String key) throws IOException {
        return getProperty(key, "src/test/resources/configfiles/config.properties");
    }

    // Acceso específico a locators.properties
    public static String getLocator(String key) throws IOException {
        return getProperty(key, "src/test/resources/configfiles/locators.properties");
    }
}

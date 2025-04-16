package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class NavigationUtils {

    public String buildUrl(String key) throws IOException {
        FileReader fr = new FileReader("src/main/resources/configfiles/config.properties");
        Properties pr = new Properties();
        pr.load(fr);
        String base = pr.getProperty("url");
        return base + pr.getProperty(key);
    }
}

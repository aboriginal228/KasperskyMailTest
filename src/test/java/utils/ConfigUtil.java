package utils;

import org.openqa.selenium.Dimension;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

    private static Properties property;

    static {
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties")){
            property = new Properties();
            property.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Config is not loaded");
        }
    }

    public static String getDefaultUrl() {
        return property.getProperty("default-url");
    }

    public static Dimension getDefaultSize() {
        return new Dimension(
                Integer.parseInt(property.getProperty("width")),
                Integer.parseInt(property.getProperty("height"))
                );
    }

    public static String getMailUsername() {
        return property.getProperty("mail-username");
    }

    public static String getMailPassword() {
        return property.getProperty("mail-password");
    }

    public static String getTestPassword() {
        return property.getProperty("test-password");
    }

    public static String getLinkTextToCheck() {
        return property.getProperty("link-text");
    }

    public static String getMailHost() {
        return property.getProperty("mail-host");
    }

    public static String getMailPort() {
        return property.getProperty("mail-port");
    }

}

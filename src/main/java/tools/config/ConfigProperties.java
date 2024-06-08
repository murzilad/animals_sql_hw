package tools.config;

import tools.config.IConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigProperties implements IConfig {

    public Map<String, String> getConfig() {
        Properties properties = new Properties();

        String filePath = System.getProperty("user.dir") + "/src/main/resources/db.properties"; //динамический путь к проекту

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(filePath);
            properties.load(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return convertProperties(properties);
    }

    private Map<String, String> convertProperties(Properties properties) {

        Map props = new HashMap<String, String>();

        for (Map.Entry entry: properties.entrySet()) {
            props.put(entry.getKey(), entry.getValue());
        }
        return props;
    }

}

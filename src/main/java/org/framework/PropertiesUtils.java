package org.framework;

import org.apache.commons.io.FileUtils;
import utils.ReportManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {

    public static final String PROPERTIES_PATH = "src/main/resources/properties";

    private PropertiesUtils() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFiles = FileUtils.listFiles(new File(PROPERTIES_PATH), new String[]{"properties"}, true);
            propertiesFiles.forEach(file -> {
                try {
                    properties.load(new FileInputStream(file));
                } catch (IOException e) {
                    ReportManager.error(e.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            ReportManager.info("Loading properties file data: ", properties.toString());
            return properties;
        } catch (Exception e) {
            ReportManager.error("Failed to Load Properties File data because: " + e.getMessage());
            return null;
        }
    }

    public static String getPropertyValue(String key) {
        try {
            return System.getProperty(key);
        } catch (Exception e) {
            ReportManager.error("Failed to get property value because: " + e.getMessage());
            return null;
        }

    }
}

package py.com.softwave.erpfx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ConfigManager {
    private static final String CLASS_NAME = ConfigManager.class.getName();
    private static final String EXTERNAL_CONFIG_FILE = "config.properties";
    private static final String INTERNAL_CONFIG_FILE = "/default-config.properties";
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
    private static final Properties PROPERTIES = new Properties();
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages", java.util.Locale.getDefault());

    static {
        try {
            File externalFile = new File(EXTERNAL_CONFIG_FILE);
            if (externalFile.exists()) {
                try (FileInputStream fis = new FileInputStream(externalFile)) {
                    PROPERTIES.load(fis);
                    LoggerUtil.logInfo(LOGGER, "info.externalPropertiesLoaded");
                }
            } else {
                try (var internalStream = ConfigManager.class.getResourceAsStream(INTERNAL_CONFIG_FILE)) {
                    if (internalStream != null) {
                        PROPERTIES.load(internalStream);
                        LoggerUtil.logInfo(LOGGER, "info.internalPropertiesLoaded");
                    } else {
                        throw new IOException(String.format(MESSAGES.getString("warning.fileCreation"), INTERNAL_CONFIG_FILE));
                    }
                }
            }
        } catch (IOException e) {
            LoggerUtil.logSevere(LOGGER, MESSAGES.getString("severe.configurationLoad") + " " + e.getMessage());
        }
    }

    private ConfigManager() {
        throw new UnsupportedOperationException(String.format(MESSAGES.getString("severe.classInstantiation"), CLASS_NAME));
    }

    public static String getProperty(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }
}

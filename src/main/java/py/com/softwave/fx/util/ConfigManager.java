package py.com.softwave.fx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigManager {
    private static final String EXTERNAL_CONFIG_FILE = "config.properties";
    private static final String INTERNAL_CONFIG_FILE = "/default-config.properties";
    private static final String CLASS_NAME = ConfigManager.class.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
    private static final Properties PROPERTIES = new Properties();

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
                        throw new IOException(String.format(DefaultValues.MESSAGES.getString("warning.fileCreation"), INTERNAL_CONFIG_FILE));
                    }
                }
            }
        } catch (IOException e) {
            LoggerUtil.logSevere(LOGGER, DefaultValues.MESSAGES.getString("severe.configurationLoad") + " " + e.getMessage());
        }
    }

    private ConfigManager() {
        throw new UnsupportedOperationException(String.format(DefaultValues.MESSAGES.getString("severe.classInstantiation"), CLASS_NAME));
    }

    public static String getProperty(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }
}

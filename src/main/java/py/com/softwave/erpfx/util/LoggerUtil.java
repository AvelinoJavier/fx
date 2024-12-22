package py.com.softwave.erpfx.util;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerUtil {
    private static final String LOGS_FOLDER_NAME = "logs";
    private static final String CLASS_NAME = LoggerUtil.class.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages", java.util.Locale.getDefault());

    static {
        try {
            LogManager.getLogManager().readConfiguration(LoggerUtil.class.getResourceAsStream("/logging.properties"));
            File logDir = new File(LOGS_FOLDER_NAME);
            if (!logDir.exists() && !logDir.mkdir()) {
                LoggerUtil.logWarning(LOGGER, "warning.folderCreation", LOGS_FOLDER_NAME);
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private LoggerUtil() {
        throw new UnsupportedOperationException(String.format(MESSAGES.getString("severe.classInstantiation"), CLASS_NAME));
    }

    public static void logInfo(Logger logger, String messageKey, Object... args) {
        log(logger, Level.INFO, messageKey, args);
    }

    public static void logSevere(Logger logger, String messageKey, Object... args) {
        log(logger, Level.SEVERE, messageKey, args);
    }

    public static void logWarning(Logger logger, String messageKey, Object... args) {
        log(logger, Level.WARNING, messageKey, args);
    }

    private static void log(Logger logger, Level level, String messageKey, Object... args) {
        if (logger.isLoggable(level)) {
            String message = String.format(MESSAGES.getString(messageKey), args);

            LogRecord logRecord = new LogRecord(level, message);
            logRecord.setLoggerName(logger.getName());
            logRecord.setSourceClassName(getCallingClassName());
            logRecord.setSourceMethodName(getCallingMethodName());

            logger.log(logRecord);
        }
    }

    private static String getCallingClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClassName().equals(LoggerUtil.class.getName())) {
                return stackTrace[i].getClassName();
            }
        }
        return null;
    }

    private static String getCallingMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClassName().equals(LoggerUtil.class.getName())) {
                return stackTrace[i].getMethodName();
            }
        }
        return null;
    }
}

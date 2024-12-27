package py.com.softwave.fx.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBConnection {
    private static final String DATA_FOLDER = "data";
    private static final String URL = "jdbc:sqlite:" + DATA_FOLDER + "/database.db";
    private static final String CLASS_NAME = ConfigManager.class.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    private DBConnection() {
        throw new UnsupportedOperationException(String.format(DefaultValues.MESSAGES.getString("severe.classInstantiation"), CLASS_NAME));
    }

    public static Connection connect() {
        if (!new File(DATA_FOLDER).mkdirs()) {
            LoggerUtil.logWarning(LOGGER, DefaultValues.MESSAGES.getString("warning.folderCreation"), DATA_FOLDER);
        }
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            LoggerUtil.logSevere(LOGGER, DefaultValues.MESSAGES.getString("severe.dbConnection") + " " + e.getMessage());
            return null;
        }
    }
}

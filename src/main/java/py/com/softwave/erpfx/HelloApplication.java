package py.com.softwave.erpfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import py.com.softwave.erpfx.util.ConfigManager;
import py.com.softwave.erpfx.util.LoggerUtil;

import java.io.IOException;
import java.util.logging.Logger;

public class HelloApplication extends Application {
    private static final String CLASS_NAME = HelloApplication.class.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(ConfigManager.getProperty("hello.title", CLASS_NAME));
        stage.setScene(scene);
        stage.show();
        LoggerUtil.logInfo(LOGGER, "info.appInit");
    }

    public static void main(String[] args) {
        launch();
    }
}
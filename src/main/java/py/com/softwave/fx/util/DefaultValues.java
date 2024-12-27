package py.com.softwave.fx.util;

import java.util.ResourceBundle;

public class DefaultValues {
    private static final String CLASS_NAME = ConfigManager.class.getName();

    public static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages", java.util.Locale.getDefault());
    public static final int DEFAULT_SCENE_WIDTH = 320;
    public static final int DEFAULT_SCENE_HEIGHT = 240;

    private DefaultValues() {
        throw new UnsupportedOperationException(String.format(MESSAGES.getString("severe.classInstantiation"), CLASS_NAME));
    }
}

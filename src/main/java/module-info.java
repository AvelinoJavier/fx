module py.com.softwave.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens py.com.softwave.fx to javafx.fxml;
    exports py.com.softwave.fx;
    exports py.com.softwave.fx.controller;
    opens py.com.softwave.fx.controller to javafx.fxml;
}
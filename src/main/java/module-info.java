module py.com.softwave.erpfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.logging;

    opens py.com.softwave.erpfx to javafx.fxml;
    exports py.com.softwave.erpfx;
    exports py.com.softwave.erpfx.controller;
    opens py.com.softwave.erpfx.controller to javafx.fxml;
}
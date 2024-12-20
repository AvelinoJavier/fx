module py.com.softwave.erpfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens py.com.softwave.erpfx to javafx.fxml;
    exports py.com.softwave.erpfx;
}
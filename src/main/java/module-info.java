module com.proj.log {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;

    requires org.kordamp.bootstrapfx.core;


    opens com.proj.log to javafx.fxml;
    exports com.proj.log;
}
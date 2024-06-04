module net.yakodan.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens net.yakodan.project to javafx.fxml;
    exports net.yakodan.project;
}
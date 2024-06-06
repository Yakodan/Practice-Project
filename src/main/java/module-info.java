module net.yakodan.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jfree.jfreechart;


    opens net.yakodan.project to javafx.fxml;
    exports net.yakodan.project;
    exports net.yakodan.project.fxdialogs;
    opens net.yakodan.project.fxdialogs to javafx.fxml;
    exports net.yakodan.project.tasks;
    opens net.yakodan.project.tasks to javafx.fxml;
}
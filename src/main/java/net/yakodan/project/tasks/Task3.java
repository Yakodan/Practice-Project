package net.yakodan.project.tasks;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import net.yakodan.project.fxdialogs.FxDialogs;


public class Task3 extends Application {

    @FXML
    private Label dshLabel;
    @FXML
    private Slider dshSlider;

    @FXML
    public void setDshLabel(){
        double value = dshSlider.getValue();
        String dsh = String.format("%sмм", value);
        dshLabel.setText(dsh);
    }

    @FXML
    public void showAnswer(){
        FxDialogs.showInformation("Результат", String.format("\"er\" равно: %fмм", calculateER(dshSlider.getValue())));
    }

    public double calculateER(double dsh){
        return (double) Math.round(0.000009/4*(24-dsh)*1000000)/1000000;
    }

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Task5.class.getResource("task3.fxml"));
            Parent root = fxmlLoader.load();
            stage.setTitle("Задание 3");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

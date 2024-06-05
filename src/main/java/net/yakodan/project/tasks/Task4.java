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

public class Task4 extends Application {

    @FXML
    private Label dshLabel;
    @FXML
    private Slider dshSlider;
    @FXML
    private Label e0Label;
    @FXML
    private Slider e0Slider;

    @FXML
    public void setDshLabel(){
        double value = dshSlider.getValue();
        String dsh = String.format("%sмм", value);
        dshLabel.setText(dsh);
    }

    @FXML
    public void setE0Label(){
        double value = e0Slider.getValue()/1000;
        String e0 = String.format("%sмм", value);
        e0Label.setText(e0);
    }

    @FXML
    public void showAnswer(){
        FxDialogs.showInformation("Результат",
                String.format("\"er\" равно: %fмм", calculateER(dshSlider.getValue(),e0Slider.getValue()/1000)));
    }


    public double calculateER(double dsh, double e0){
        return (double) Math.round(e0*e0/4*(24-dsh)*1000000)/1000000;
    }


    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Task5.class.getResource("task4.fxml"));
            Parent root = fxmlLoader.load();
            stage.setTitle("Задание 4");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

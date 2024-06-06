package net.yakodan.project.tasks;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.yakodan.project.fxdialogs.FxDialogs;

public class Task1 extends Application {

    @FXML
    private TextField inputA;
    @FXML
    private TextField inputB;
    @FXML
    private TextField inputR;

    @FXML
    public void showR(){
        double A;
        double B;
        double R;
        try{
            A = Double.parseDouble(inputA.getText());
            B = Double.parseDouble(inputB.getText());
            R = Double.parseDouble(inputR.getText());
        } catch (NumberFormatException e){
            FxDialogs.showError("Ошибка","Неверено введённое число!");
            return;
        }

        FxDialogs.showInformation("Результат","\"r\" равно: " + calculateR(A,B,R));
        Stage stage = (Stage) inputA.getScene().getWindow();
        stage.show();
    }

    public double calculateR(double A, double B, double R){
        return (double) Math.round((A + R - B - 1.4142*Math.sqrt(2*R+A-B))*10000)/10000;
    }

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Task1.class.getResource("task1.fxml"));
            Parent root = fxmlLoader.load();
            stage.setTitle("Задание 1");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

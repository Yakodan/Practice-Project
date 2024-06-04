package net.yakodan.project;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class Task1Controller {

    @FXML
    public TextField inputA;
    public TextField inputB;
    public TextField inputR;

    @FXML
    public void showCalcR(){
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
}

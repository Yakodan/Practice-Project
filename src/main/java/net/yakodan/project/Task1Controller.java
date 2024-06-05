package net.yakodan.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.yakodan.project.fxdialogs.FxDialogs;

public class Task1Controller {

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
}

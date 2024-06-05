package net.yakodan.project;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.yakodan.project.fxdialogs.FxDialogs;

public class Task2Controller {

    @FXML
    private TextField inputQ0;
    @FXML
    private TextField inputAlf;
    @FXML
    private TextField inputD0;
    @FXML
    private ToggleGroup puffGroup;
    @FXML
    private CheckBox lubeCheckBox;

    @FXML
    public void showMt(){
        double q0;
        double alf;
        double d0;
        try{
            q0 = Double.parseDouble(inputQ0.getText());
            alf = Double.parseDouble(inputAlf.getText());
            d0 = Double.parseDouble(inputD0.getText());
        } catch (NumberFormatException e){
            FxDialogs.showError("Ошибка","Неверено введённое число!");
            return;
        }

        double ft = 0;

        RadioButton selectedButton = (RadioButton) puffGroup.getSelectedToggle();

        if(lubeCheckBox.isSelected()){
            switch (selectedButton.getText()){
                case "Первая затяжка" -> ft = 0.13;
                case "Десятая затяжка" -> ft = 0.11;
            }
        } else {
            switch (selectedButton.getText()){
                case "Первая затяжка" -> ft = 0.16;
                case "Десятая затяжка" -> ft = 0.22;
            }
        }

        FxDialogs.showInformation("Результат","\"Mт\" равно: " + calculateMt(q0,ft,alf,d0));
        Stage stage = (Stage) inputQ0.getScene().getWindow();
        stage.show();
    }

    public double calculateMt(double q0, double ft, double alf, double d0){
        return (double) Math.round(q0*ft*((alf+d0)/2)*10000)/10000;
    }
}

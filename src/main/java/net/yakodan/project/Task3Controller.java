package net.yakodan.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import net.yakodan.project.fxdialogs.FxDialogs;


public class Task3Controller {

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
}

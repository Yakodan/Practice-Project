package net.yakodan.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class Task4Controller {

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

}

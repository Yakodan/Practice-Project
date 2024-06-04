package net.yakodan.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class Task3Controller {

    @FXML
    private Label answerLabel;

    @FXML
    public void showAnswer(){
        String answer = new String();
        answer += "R = 12мм, e0 = 0.003мм\n";
        for(double dsh = 8.0;dsh<=10.0;dsh+=0.5){
            double er = calculateER(dsh);
            answer += String.format("dш = %sмм: er = %fмм\n", dsh, er);
        }
        answerLabel.setText(answer);
    }

    public double calculateER(double dsh){
        return (double) Math.round(0.000009/4*(24-dsh)*1000000)/1000000;
    }
}

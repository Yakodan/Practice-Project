package net.yakodan.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class Task5Controller {

    @FXML
    private TextField numDetailField;
    @FXML
    private TextField detailMinValueField;
    @FXML
    private TextField detailMaxValueField;
    @FXML
    private TextField numGroupField;

    @FXML
    public void showResult(){
        int numDetail = Integer.parseInt(numDetailField.getText());
        int numGroup = Integer.parseInt(numGroupField.getText());
        double minValue = Double.parseDouble(detailMinValueField.getText());
        double maxValue = Double.parseDouble(detailMaxValueField.getText());
    }

    private ArrayList<Double>[] sortDetailsToGroups(ArrayList<Double> details, int n)

}

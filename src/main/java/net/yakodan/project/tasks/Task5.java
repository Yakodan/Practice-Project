package net.yakodan.project.tasks;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.yakodan.project.fxdialogs.FxDialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class Task5 extends Application {

    @FXML
    private TextField numDetailField;
    @FXML
    private TextField detailMinValueField;
    @FXML
    private TextField detailMaxValueField;
    @FXML
    private TextField numGroupField;

    @FXML
    public void showResult() {
        int numDetail;
        int numGroup;
        double minValue;
        double maxValue;
        try {
            numDetail = Integer.parseInt(numDetailField.getText());
            numGroup = Integer.parseInt(numGroupField.getText());
            minValue = Double.parseDouble(detailMinValueField.getText());
            maxValue = Double.parseDouble(detailMaxValueField.getText());
        } catch (NumberFormatException e) {
            FxDialogs.showError("Ошибка!", "Неверно введённые числовые значения!");
            return;
        }
        if (numDetail <= 0 || minValue <= 0 || maxValue <= 0 || numGroup <= 0) {
            FxDialogs.showError("Ошибка!", "Значения не могут быть отрицательными!");
            return;
        }
        if (minValue > maxValue) {
            FxDialogs.showError("Ошибка!", "Неверно указаны границы значений!");
            return;
        }

        ArrayList<Double> details = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numDetail; i++) {
            double detail = (double) Math.round((random.nextDouble(maxValue - minValue) + minValue) * 100) / 100;
            details.add(detail);
        }
        Collections.sort(details);

        ArrayList<Double>[] groups = sortDetailsToGroups(details, numGroup, minValue, maxValue);

        String result = String.format("Изначальные значения: %s\n\nРаспределение по группам:\n", details);
        double h = (maxValue - minValue) / numGroup;
        int i = 0;
        for (ArrayList<Double> group : groups) {
            result += String.format("[%.2f, %.2f] : %s\n", minValue + i * h, minValue + (i + 1) * h, group);
            i++;
        }

        FxDialogs.showInformation("Результат распределения", result);
    }

    public ArrayList<Double>[] sortDetailsToGroups(ArrayList<Double> details, int numGroup, double minValue, double maxValue) {
        ArrayList<Double>[] groups = new ArrayList[numGroup];
        double h = (maxValue - minValue) / numGroup;

        for (int i = 0; i < numGroup; i++) {
            groups[i] = new ArrayList<>();
        }

        int i = 0;
        for (double detail : details) {
            if (detail >= minValue + (i+1)*h){
                i++;
            }
            groups[i].add(detail);
        }

        return groups;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Task5.class.getResource("task5.fxml"));
            Parent root = fxmlLoader.load();
            stage.setTitle("Задание 5");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

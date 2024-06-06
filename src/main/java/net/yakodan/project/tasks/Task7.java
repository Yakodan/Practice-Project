package net.yakodan.project.tasks;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.function.Function;

public class Task7 extends Application {

    @FXML
    private Label rectangleLabel;
    @FXML
    private Label trapezeLabel;
    @FXML
    private Label simpsonLabel;

    @FXML
    public void showResults() {
        Function<Double,Double> function = x-> Math.cos(Math.exp(x/3)+x);
        double left = 0;
        double right = 3;
        int n = 6;

        double rectangleResult = calculateRectangleIntegral(function,left,right,n);
        double trapezeResult = calculateTrapezeIntegral(function,left,right,n);
        double simpsonResult = calculateSimpsonIntegral(function,left,right,n);

        rectangleLabel.setText(String.format("Формула левых прямоугольников: %s", rectangleResult));
        trapezeLabel.setText(String.format("Формула трапеций: %s", trapezeResult));
        simpsonLabel.setText(String.format("Формула Симпсона: %s", simpsonResult));
    }

    public double calculateRectangleIntegral(Function<Double, Double> function, double left, double right, int n) {
        double h = (right - left) / n;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += h * function.apply(left + i * h);
        }
        return (double) Math.round(sum * 10000) / 10000;
    }

    public double calculateTrapezeIntegral(Function<Double, Double> function, double left, double right, int n) {
        double h = (right - left) / n;
        double sum = h * (function.apply(left) + function.apply(right)) / 2;
        for (int i = 1; i < n; i++) {
            sum += h * function.apply(left + i * h);
        }
        return (double) Math.round(sum * 10000) / 10000;
    }

    public double calculateSimpsonIntegral(Function<Double, Double> function, double left, double right, int n) {
        double h = (right - left) / n;
        double sum = h * (function.apply(left) + function.apply(right)) / 3;
        for (int i = 1; i < n; i++) {
            if(i%2==0){
                sum+= h*2*function.apply(left+i*h)/3;
            } else {
                sum+= h*4*function.apply(left+i*h)/3;
            }
        }
        return (double) Math.round(sum * 10000) / 10000;
    }


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Task7.class.getResource("task7.fxml"));
            Parent root = fxmlLoader.load();
            stage.setTitle("Задание 7");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package net.yakodan.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.yakodan.project.tasks.Task6;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button task1Button;
    @FXML
    private Button task2Button;
    @FXML
    private Button task3Button;
    @FXML
    private Button task4Button;
    @FXML
    private Button task5Button;
    @FXML
    private Button task6Button;
    @FXML
    private Button task7Button;

    @FXML
    public void onTask1ButtonClicked(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(net.yakodan.project.MainApplication.class.getResource("tasks/task1.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Задание 1");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(task1Button.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onTask2ButtonClicked(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(net.yakodan.project.MainApplication.class.getResource("tasks/task2.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Задание 2");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(task2Button.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onTask3ButtonClicked(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(net.yakodan.project.MainApplication.class.getResource("tasks/task3.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Задание 3");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(task3Button.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onTask4ButtonClicked(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(net.yakodan.project.MainApplication.class.getResource("tasks/task4.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Задание 4");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(task4Button.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void setTask5ButtonClicked(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(net.yakodan.project.MainApplication.class.getResource("tasks/task5.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Задание 5");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(task5Button.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setTask6ButtonClicked(){
        Task6.showResult();
    }

    @FXML
    public void setTask7ButtonClicked(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(net.yakodan.project.MainApplication.class.getResource("tasks/task7.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Задание 7");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(task7Button.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* TODO - do some code */
    }
}
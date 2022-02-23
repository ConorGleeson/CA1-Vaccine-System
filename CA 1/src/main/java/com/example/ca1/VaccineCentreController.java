package com.example.ca1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



import javafx.stage.Stage;


import java.io.IOException;



public class VaccineCentreController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField eircode;

    @FXML
    private TextField numParking;

    @FXML
    private Button add;

    @FXML
    private Button booths;

    @FXML
    private Button back;

    @FXML
    private ListView centres;


    @FXML
    public void initialize() {
        try {



            for (VaccineCentre centre : Driver.getMyLinkedListAPI().getVaccineCentreList()) {
                centres.getItems().add(centre.toString()); //toString

           }
        }
        catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }



}

    @FXML
    public void confirm() {
        try {
            centres.getItems().add("Name: " + name.getText() + " " + "Address: " + address.getText() + " " + "Eircode: " + eircode.getText() + " " + "Number of Parking Spots: " + numParking.getText());


            VaccineCentre centerObject = new VaccineCentre(name.getText(), address.getText(), eircode.getText(), numParking.getText());
            Driver.getMyLinkedListAPI().getVaccineCentreList().addElement(centerObject);
        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }

    @FXML
    public void delete() {
        try {

            if (centres.getSelectionModel().getSelectedIndex() >= 0)
                centres.getItems().remove(centres.getSelectionModel().getSelectedIndex());

            Driver.getMyLinkedListAPI().getVaccineCentreList().deleteElement(centres.getSelectionModel().getSelectedIndex());
        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }


    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToBooths(ActionEvent event) throws IOException {
        if (Driver.getMyLinkedListAPI().getVaccineCentreList().size() > 0) {
            Parent root = FXMLLoader.load(getClass().getResource("VaccineBoothView.fxml"));
            stage = (Stage) ((Node) event.getSource         ()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }



}

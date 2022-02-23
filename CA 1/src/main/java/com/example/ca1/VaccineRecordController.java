package com.example.ca1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;



public class VaccineRecordController {



    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private ChoiceBox patients;

    @FXML
    private ListView recordsList;

    @FXML
    private Button home;


    public void initialize() {
        try {

            for (Patient patient : Driver.getMyLinkedListAPI().getPatientsList()) {
                patients.getItems().add(patient.getPpsNumber());
            }
            patients.valueProperty().addListener((ov, oldValue, newValue) -> populateRecords());

        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }

    public void populateRecords(){
        try {
            recordsList.getItems().clear();
            int patientIndex = patients.getSelectionModel().getSelectedIndex();
            System.out.println(patientIndex);
            for (VaccineRecord record : Driver.getMyLinkedListAPI().getPatient(patientIndex).getRecordList()) {
                recordsList.getItems().add(record.toString());
            }
        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }




    public void switchToHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToAppointments(ActionEvent event) throws  IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AppointmentView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPatients(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PatientView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

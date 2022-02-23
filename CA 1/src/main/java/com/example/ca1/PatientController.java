package com.example.ca1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TextField ppsNumber;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField number;
    @FXML
    public TextField email;

    @FXML
    public Button back;
    @FXML
    public Button delete;
    @FXML
    public Button add;
    @FXML
    private  Button patientRecords;
    @FXML
    private Button appointments;
    @FXML
    public ListView patientsList;

    @FXML
    public DatePicker date;

    @FXML
    public ChoiceBox wheelChair;

    @FXML
    public void initialize(){
        try {


            for (Patient patient : Driver.getMyLinkedListAPI().getPatientsList()) {
                patientsList.getItems().add(patient.toString());
            }

            wheelChair.getItems().addAll("Yes", "No");
        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }


    public void addNewPatient() {
        try {


            patientsList.getItems().add("PPS Number: " + ppsNumber.getText() + " Name: " + firstName.getText() + " " + lastName.getText() + " Date of Birth: " + date.getValue() + " Phone: " + number.getText() + " Email: " + email.getText() + " Wheelchair: " + wheelChair.getValue().toString());

            Patient patientObject = new Patient(ppsNumber.getText(), firstName.getText(), lastName.getText(), date.getValue(), number.getText(), email.getText(), wheelChair.getValue().toString());
            Driver.getMyLinkedListAPI().getPatientsList().addElement(patientObject);
        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void delete() {
        try {

            if (patientsList.getSelectionModel().getSelectedIndex() >= 0)
                patientsList.getItems().remove(patientsList.getSelectionModel().getSelectedIndex());
            Driver.getMyLinkedListAPI().getPatientsList().deleteElement(patientsList.getSelectionModel().getSelectedIndex());
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

    public void switchToRecords(ActionEvent event) throws  IOException{
        Parent root = FXMLLoader.load(getClass().getResource("VaccineRecordView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToAppointments(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AppointmentView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

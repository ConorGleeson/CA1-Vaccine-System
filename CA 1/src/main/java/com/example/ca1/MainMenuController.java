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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class MainMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField search;

    @FXML
    private Button close;

    @FXML
    private Button vacCentre;

    @FXML
    private Button nuke;

    @FXML
    private Button refresh;

    @FXML
    private ListView listOfAppointments;

    @FXML
    private ListView searchList;


    public void exit() {
        try {

            System.exit(0);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void switchToVaccineCentre(ActionEvent event) throws IOException {    //https://youtu.be/hcM-R-YOKkQ how to switch scenes
        Parent root = FXMLLoader.load(getClass().getResource("VaccineCentreView.fxml"));
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

    public void nuke() { //runs the clear list method to completely empty all info from the system
        try {
            Driver.getMyLinkedListAPI().getVaccineCentreList().clearList();
            Driver.getMyLinkedListAPI().getPatientsList().clearList();
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }

    public void search() {
        try {

            //searchList.getItems().clear(); //clears old search results
            String searchValue = search.getText().toLowerCase(); //value entered by the user to be searched

            for (VaccineCentre centre : Driver.getMyLinkedListAPI().getVaccineCentreList()) {
                for (VaccineBooth booth : centre.getVaccineBoothList()) {
                    for (Appointment appointment : booth.getAppointmentsList()) {
                        if (appointment.getVaccineType().toLowerCase().contains(searchValue) ||
                                appointment.getVaccineIdentifier().toLowerCase().contains(searchValue)) { //checks to see if the value entered by the user is contained within either the type or batch number of the record
                            searchList.getItems().clear();
                            searchList.getItems().add("Pending Appointment");
                            searchList.getItems().add(appointment.toString());

                        }
                    }
                }
            }
            for (Patient patient : Driver.getMyLinkedListAPI().getPatientsList()) {
                for (VaccineRecord record : patient.getRecordList()) {
                    if (record.getType().toLowerCase().contains(searchValue) ||
                            record.getBatch().toLowerCase().contains(searchValue)) {
                        searchList.getItems().clear();
                        searchList.getItems().add("Completed Appointment");
                        searchList.getItems().add(record.toString());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }

    public void refresh() { //gets all uncompleted appointments and adds them to the list view
        try {
            listOfAppointments.getItems().clear();
            for (VaccineCentre centre : Driver.getMyLinkedListAPI().getVaccineCentreList()) {
                for (VaccineBooth booth : centre.getVaccineBoothList()) {
                    for (Appointment appointment : booth.getAppointmentsList()) {
                        listOfAppointments.getItems().add("Centre: " + centre.getName() + " Booth Identifier: " + appointment.getBoothIdentifier() + " Patient PPSN: " + appointment.getPatientPPSN() + " Date: " + appointment.getDate() + " Vaccine Type: " + appointment.getVaccineType());

                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void save() throws IOException {
        Driver.save();

    }

    public void load() throws IOException, ClassNotFoundException {
        Driver.load();

    }
}

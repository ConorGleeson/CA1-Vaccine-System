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

public class AppointmentController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox centreList;

    @FXML
    private ChoiceBox boothList;

    @FXML
    private ChoiceBox patientList;

    @FXML
    private Button back;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox vaccineType;

    @FXML
    private ChoiceBox time;

    @FXML
    private TextField vaccineIdentifier;

    @FXML
    private TextField vaccinatorDetails;

    @FXML
    private ListView appointmentsList;

    @FXML
    private Button complete;

    @FXML
    private Button appointmentRecords;


    public void initialize() throws IOException {
        try {
            boothList.valueProperty().addListener((ov, oldValue, newValue) -> populateAppointmentList());
            centreList.valueProperty().addListener((ov, oldValue, newValue) -> populateBooths());    // https://www.dummies.com/programming/java/javafx-how-to-use-property-events/ using listener to update choice box
            patientList.valueProperty().addListener((ov, oldValue, newValue) -> populateBooths());

            for (Patient patient : Driver.getMyLinkedListAPI().getPatientsList()) {
                patientList.getItems().add(patient.getPpsNumber() +" " + patient.getFirstName() +" " + patient.getLastName());

            }


            for (VaccineCentre centre : Driver.getMyLinkedListAPI().getVaccineCentreList()) {
                centreList.getItems().add(centre.getName());
            }


            vaccineType.getItems().addAll("Pfizer", "AstraZeneca", "Moderna", "Johnson & Johnson");

            time.getItems().addAll("9.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00");
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }


    }

    public void populateBooths() {
        try {

            boothList.getItems().clear(); // makes sure list is always empty before adding
            int index = centreList.getSelectionModel().getSelectedIndex();


                for (VaccineBooth booth : Driver.getMyLinkedListAPI().getVaccineCentre(index).getVaccineBoothList()) {
                    boothList.getItems().add(booth.getIdentifier() + " Wheelchair Accessible: " + booth.getWheelchairAccessible());
                }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }


    public void back(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("VaccineBoothView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void add() {
        try {
            if (Driver.getMyLinkedListAPI().getPatientsList().size() > 0) {
                int centreIndex = centreList.getSelectionModel().getSelectedIndex();
                VaccineCentre chosenCentre = Driver.getMyLinkedListAPI().getVaccineCentre(centreIndex);


                int boothIndex = boothList.getSelectionModel().getSelectedIndex();
                VaccineBooth chosenBooth = chosenCentre.getVaccineBooth(boothIndex);

                int patientIndex = patientList.getSelectionModel().getSelectedIndex();
                Patient chosenPatient = Driver.getMyLinkedListAPI().getPatient(patientIndex);

                appointmentsList.getItems().add("Booth Num: " + chosenBooth.getIdentifier() + " Patient PPSN: " + chosenPatient.getPpsNumber() + " Date: " + date.getValue().toString() + " Time: " + time.getValue() + " Vaccine Type: " + vaccineType.getValue() + " Vaccine Identifier: " + vaccineIdentifier.getText() + " Vaccinator Details: " + vaccinatorDetails.getText());


                Appointment appointment = new Appointment(chosenBooth.getIdentifier(), chosenPatient.getPpsNumber(), date.getValue(), time.getValue() + " ", vaccineType.getValue() + " ", vaccineIdentifier.getText(), vaccinatorDetails.getText());
                Driver.getMyLinkedListAPI().getVaccineCentre(centreIndex).getVaccineBooth(boothIndex).getAppointmentsList().addElement(appointment);

            }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }


    public void populateAppointmentList() {
        try {
            appointmentsList.getItems().clear();
            int centreIndex = centreList.getSelectionModel().getSelectedIndex();
            int boothIndex = boothList.getSelectionModel().getSelectedIndex();

            for (Appointment appointments : Driver.getMyLinkedListAPI().getVaccineCentre(centreIndex).getVaccineBooth(boothIndex).getAppointmentsList()) {
                appointmentsList.getItems().add(appointments.toString()); //adds all the appointments of a specific booth to the list view
            }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }


    public void delete() {
        try {
            int centreIndex = centreList.getSelectionModel().getSelectedIndex();
            VaccineCentre chosenCentre = Driver.getMyLinkedListAPI().getVaccineCentre(centreIndex);

            int boothIndex = boothList.getSelectionModel().getSelectedIndex();
            VaccineBooth chosenBooth = chosenCentre.getVaccineBooth(boothIndex);

            if (appointmentsList.getSelectionModel().getSelectedIndex() >= 0) {
                appointmentsList.getItems().remove(appointmentsList.getSelectionModel().getSelectedIndex());

                chosenBooth.getAppointmentsList().deleteElement(appointmentsList.getSelectionModel().getSelectedIndex());
            }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void completeAppointment() {
        try {

            int centreIndex = centreList.getSelectionModel().getSelectedIndex();
            VaccineCentre chosenCentre = Driver.getMyLinkedListAPI().getVaccineCentre(centreIndex);

            int boothIndex = boothList.getSelectionModel().getSelectedIndex();
            VaccineBooth chosenBooth = chosenCentre.getVaccineBooth(boothIndex);

            int appointmentIndex = appointmentsList.getSelectionModel().getSelectedIndex();
            Appointment chosenAppointment = chosenBooth.getAppointment(appointmentIndex);

            int patientIndex = patientList.getSelectionModel().getSelectedIndex();


            //creates a new vaccine record based off of  the details entered the appointment and adds to the record linked list
            VaccineRecord recordObject = new VaccineRecord(chosenAppointment.getDate(), chosenAppointment.getVaccineIdentifier(), chosenAppointment.getVaccineType(), chosenAppointment.getVaccinatorDetails(), chosenAppointment.getPatientPPSN());
            Driver.getMyLinkedListAPI().getPatient(patientIndex).getRecordList().addElement(recordObject);

            //removes the old appointment from the linked list and the listview
            appointmentsList.getItems().remove(appointmentsList.getSelectionModel().getSelectedIndex());
            chosenBooth.getAppointmentsList().deleteElement(appointmentsList.getSelectionModel().getSelectedIndex());
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }

    public void switchToRecords(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("VaccineRecordView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }




    public void smartSelect() { //picks the booth for the user based on if the patient requires a wheelchair or not
        try {

            int index = centreList.getSelectionModel().getSelectedIndex();


            int patientIndex = patientList.getSelectionModel().getSelectedIndex();
            Patient chosenPatient = Driver.getMyLinkedListAPI().getPatient(patientIndex);


            if (Driver.getMyLinkedListAPI().getPatient(patientIndex).getWheelchairUser().equals("Yes")) { //checks to see if the patient requires a wheelchair
                for (VaccineBooth booth : Driver.getMyLinkedListAPI().getVaccineCentre(index).getVaccineBoothList()) {
                    if (booth.getWheelchairAccessible() == "Yes") { //makes sure that the booth is wheelchair accessible
                        VaccineBooth chosenBooth = booth;


                        appointmentsList.getItems().add("Booth Num: " + chosenBooth.getIdentifier() + " Patient PPSN: " + chosenPatient.getPpsNumber() + " Date: " + date.getValue().toString() + " Time: " + time.getValue() + " Vaccine Type: " + vaccineType.getValue() + " Vaccine Identifier: " + vaccineIdentifier.getText() + " Vaccinator Details: " + vaccinatorDetails.getText());

                        int boothIndex = Driver.getMyLinkedListAPI().getVaccineCentre(index).indexOfBooth(booth);


                        Appointment appointment = new Appointment(chosenBooth.getIdentifier(), chosenPatient.getPpsNumber(), date.getValue(), time.getValue() + " ", vaccineType.getValue() + " ", vaccineIdentifier.getText(), vaccinatorDetails.getText());
                        Driver.getMyLinkedListAPI().getVaccineCentre(index).getVaccineBooth(boothIndex).getAppointmentsList().addElement(appointment);


                    }
                }
            } else
                for (VaccineBooth booth : Driver.getMyLinkedListAPI().getVaccineCentre(index).getVaccineBoothList()) {
                    if (booth.getWheelchairAccessible().equals("No")) { // gets the booths that are not wheelchair accessible
                        VaccineBooth chosenBooth = booth;

                        appointmentsList.getItems().add("Booth Num: " + chosenBooth.getIdentifier() + " Patient PPSN: " + chosenPatient.getPpsNumber() + " Date: " + date.getValue().toString() + " Time: " + time.getValue() + " Vaccine Type: " + vaccineType.getValue() + " Vaccine Identifier: " + vaccineIdentifier.getText() + " Vaccinator Details: " + vaccinatorDetails.getText());

                        int boothIndex = Driver.getMyLinkedListAPI().getVaccineCentre(index).indexOfBooth(booth);

                        Appointment appointment = new Appointment(chosenBooth.getIdentifier(), chosenPatient.getPpsNumber(), date.getValue(), time.getValue() + " ", vaccineType.getValue() + " ", vaccineIdentifier.getText(), vaccinatorDetails.getText());
                        Driver.getMyLinkedListAPI().getVaccineCentre(index).getVaccineBooth(boothIndex).getAppointmentsList().addElement(appointment);
                    }
                }

        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }


}

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class VaccineBoothController {

    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private TextField identifier;

    @FXML
    private TextField floorLevel;



    @FXML
    private Button back;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private ListView boothList;

    @FXML
    private ChoiceBox centreList;

    @FXML
    private Button appointments;

    @FXML
    private ChoiceBox wheelChair;




    public void initialize() {
        try {


            centreList.valueProperty().addListener((ov, oldValue, newValue) -> populateBoothList());  //listener to update the booth list

            for (VaccineCentre centre : Driver.getMyLinkedListAPI().getVaccineCentreList()) {
                centreList.getItems().add(centre.getName()); // gets list of centers and adds them to the choice box

            }

            wheelChair.getItems().addAll("Yes", "No");
        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }


    public void populateBoothList(){
        try {
            boothList.getItems().clear();
            int index = centreList.getSelectionModel().getSelectedIndex();
            for (VaccineBooth booth : Driver.getMyLinkedListAPI().getVaccineCentre(index).getVaccineBoothList()) { //gets the booths from a specific centre
                boothList.getItems().add(booth.toString());
            }
        }
        catch (Exception e) {
                System.err.println("Error " + e.getMessage());
            }
    }


    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("VaccineCentreView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void addBooth() {
        try {

            int index = centreList.getSelectionModel().getSelectedIndex();
            String centreName1 = Driver.getMyLinkedListAPI().getVaccineCentre(index).getName();
            boothList.getItems().add("Centre: " + centreName1 + "Booth Identifier: " + identifier.getText() + " Floor Level: " + floorLevel.getText() + " Wheelchair Accessible: " + wheelChair.getValue().toString());


            VaccineBooth boothObject = new VaccineBooth(centreName1, identifier.getText(), floorLevel.getText(), wheelChair.getValue().toString());

            Driver.getMyLinkedListAPI().getVaccineCentre(index).getVaccineBoothList().addElement(boothObject);
        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }


    public void delete() { //nightmare nightmare nightmare
        try { //moves the appointments from the deleted booth to another booth within the same centre
            int centreIndex = centreList.getSelectionModel().getSelectedIndex();
            VaccineCentre chosenCentre = Driver.getMyLinkedListAPI().getVaccineCentre(centreIndex);


                //for each gets all appointments from the booth and assigns them to a new object. new object is then added to new booth decided by the smallest ie lowest number of appointments
            for (Appointment moveApt : chosenCentre.getVaccineBooth(boothList.getSelectionModel().getSelectedIndex()).getAppointmentsList()) {
                moveApt = new Appointment(moveApt.getBoothIdentifier(), moveApt.getPatientPPSN(), moveApt.getDate(), moveApt.getTime(), moveApt.getVaccineType(), moveApt.getVaccineIdentifier(), moveApt.getVaccinatorDetails());
                if (chosenCentre.getVaccineBoothList().size() > 0) {
                    VaccineBooth smallestBooth = chosenCentre.getVaccineBooth(0);
                    for (VaccineBooth booth : chosenCentre.getVaccineBoothList()) {
                        if (booth.getAppointmentsList().size() > smallestBooth.getAppointmentsList().size()) {
                            smallestBooth = booth;
                        }
                    }
                    //each of the moving appointments is added to the smallest booth
                    smallestBooth.getAppointmentsList().addElement(moveApt);
                }
            }

            //booth is then removed
            if (boothList.getSelectionModel().getSelectedIndex() >= 0) {
                boothList.getItems().remove(boothList.getSelectionModel().getSelectedIndex());
                chosenCentre.getVaccineBoothList().deleteElement(boothList.getSelectionModel().getSelectedIndex());
            }

        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public void switchToAppointments(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AppointmentView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }




}

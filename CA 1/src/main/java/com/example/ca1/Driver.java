package com.example.ca1;

import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class Driver extends Application {
    //private static MyLinkedList<VaccineCentre> vaccineCentreList;
    //private static MyLinkedList<Patient> patientsList;
    private static MyLinkedListAPI myLinkedListAPI; //wrapper class for the main linked lists to allow for saving and loading

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("MainMenuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();

        this.myLinkedListAPI = new MyLinkedListAPI();

       // this.vaccineCentreList = new MyLinkedList<>();  //initialises the new center link list
        //this.patientsList = new MyLinkedList<>();


    }

    public static MyLinkedListAPI getMyLinkedListAPI(){
        return myLinkedListAPI;
    }





//    public static MyLinkedList<VaccineCentre> getVaccineCentreList() {
//        return vaccineCentreList;
//    }
//
//    public static VaccineCentre getVaccineCentre(int index){
//        return vaccineCentreList.get(index);
//    }
//
//    public static Patient getPatient(int index){
//        return patientsList.get(index);
//    }
//
//
//    public void setVaccineCentreList(MyLinkedList<VaccineCentre> centreList) {
//        this.vaccineCentreList = centreList;
//    }
//
//    public static MyLinkedList<Patient> getPatientsList() {
//        return patientsList;
//    }
//
//    public void setPatientsList(MyLinkedList<Patient> patientsList1) {
//        this.patientsList = patientsList1;
//    }


    public static void main(String[] args) {
        launch();
    }

    public static void save() throws IOException {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("VaccineSystem.xml"));
        out.writeObject(myLinkedListAPI);
        out.close();
    }

    public static void load() throws IOException, ClassNotFoundException {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("VaccineSystem.xml"));
        myLinkedListAPI = (MyLinkedListAPI) is.readObject();
        is.close();
    }


}



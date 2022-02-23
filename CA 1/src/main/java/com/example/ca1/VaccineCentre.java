package com.example.ca1;


public class VaccineCentre {

    private String name;
    private String address;
    private String eircode;
    private String numParking;
    private   MyLinkedList<VaccineBooth> vaccineBoothsList;


    // constructor and getters and setters todo add validation
    public VaccineCentre(String name, String address, String eircode, String numParking) {
        this.name = name;
        this.address = address;
        this.eircode = eircode;
        this.numParking = numParking;

        this.vaccineBoothsList = new MyLinkedList<>();

    }

    public  MyLinkedList<VaccineBooth> getVaccineBoothList() {
        return vaccineBoothsList;
    }

    public VaccineBooth getVaccineBooth(int index){
        return vaccineBoothsList.get(index);
    }








    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getNumParking() {
        return numParking;
    }

    public void setNumParking(String numParking) {
        this.numParking = numParking;
    }

    //todo might need an add booth method that takes in a vaccine centre



//method needed to find the index of a booth object
    public int indexOfBooth(VaccineBooth booth){
        int index = 0;

        for (VaccineBooth boothList : vaccineBoothsList){
            if(boothList.equals(booth)){
                return index;
            }
        }return -1;
    }


    @Override
    public String toString() {
        return "Name: " + name + " " + "Address: " + address + " " + "Eircode: " + eircode + " " + "Number of Parking Spots: " + numParking;
    }


}

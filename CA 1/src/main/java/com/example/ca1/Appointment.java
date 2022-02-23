package com.example.ca1;

import java.time.LocalDate;

public class Appointment {
    private String boothIdentifier;
    private LocalDate date;
    private String time;
    private String vaccineType;
    private String vaccineIdentifier;
    private String vaccinatorDetails;
    private String patientPPSN;

    MyLinkedList<VaccineRecord> recordList;


    public Appointment(String boothIdentifier, String patientPPSN, LocalDate date, String time, String vaccineType, String vaccineIdentifier, String vaccinatorDetails) {
        this.boothIdentifier = boothIdentifier;
        this.patientPPSN = patientPPSN;
        this.date = date;
        this.time = time;
        this.vaccineType = vaccineType;
        this.vaccineIdentifier = vaccineIdentifier;
        this.vaccinatorDetails = vaccinatorDetails;

        this.recordList =new MyLinkedList<>();

    }

    public MyLinkedList<VaccineRecord> getRecordList() {
        return recordList;
    }

    public VaccineRecord getRecord(int index){
        return recordList.get(index);
    }

    public String getBoothIdentifier(){
        return boothIdentifier;
    }

    public void setBoothIdentifier(){
        this.boothIdentifier = boothIdentifier;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getVaccineIdentifier() {
        return vaccineIdentifier;
    }

    public void setVaccineIdentifier(String vaccineIdentifier) {
        this.vaccineIdentifier = vaccineIdentifier;
    }

    public String getVaccinatorDetails() {
        return vaccinatorDetails;
    }

    public void setVaccinatorDetails(String vaccinatorDetails) {
        this.vaccinatorDetails = vaccinatorDetails;
    }

    public String getPatientPPSN() {
        return patientPPSN;
    }

    public void setPatientPPSN(String patientPPSN) {
        this.patientPPSN = patientPPSN;
    }

    @Override
    public String toString() {
        return "Booth Num: " + boothIdentifier + " Patient PPSN: " + patientPPSN + " Date: " + date + " Time: " + time + " Vaccine Type: " + vaccineType + " Vaccine Identifier: " + vaccineIdentifier + " Vaccinator Details: " + vaccinatorDetails ;

    }


}

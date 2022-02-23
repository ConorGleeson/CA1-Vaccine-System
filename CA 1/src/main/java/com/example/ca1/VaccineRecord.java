package com.example.ca1;

import java.time.LocalDate;

public class VaccineRecord {
    public LocalDate date;
    public String type;
    public String batch;
    public String vaccinator;
    public String patientPPS;

    public VaccineRecord(LocalDate date,String type, String batch, String vaccinator, String patientPPS) {
        this.date = date;
        this.batch = batch;
        this.type =type;
        this.vaccinator = vaccinator;
        this.patientPPS = patientPPS;


    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getVaccinator() {
        return vaccinator;
    }

    public void setVaccinator(String vaccinator) {
        this.vaccinator = vaccinator;
    }

    public String getPatientPPS() {
        return patientPPS;
    }

    public void setPatientPPS(String patientPPS) {
        this.patientPPS = patientPPS;
    }


    @Override
    public String toString() {
        return "Date " + date + " Vaccine Identifier: " + batch + " Vaccinator Details: " + "Patient PPSN:  " + patientPPS;
    }
}



package com.example.ca1;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.time.LocalDate;

public class Patient {

    private String ppsNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private String wheelchairUser;

    private  MyLinkedList<VaccineRecord> recordList;


    public Patient(String ppsNumber, String firstName, String lastName, LocalDate dateOfBirth, String phone, String email, String wheelchairUser) {
        if (Utilities.validPPS(ppsNumber)) {
            this.ppsNumber = ppsNumber;
        }else this.ppsNumber = "0000000XX";
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        if (Utilities.validEmail(email)) {
            this.email = email;
        }
        this.wheelchairUser = wheelchairUser;

        this.recordList = new MyLinkedList<>();
    }


    public  MyLinkedList<VaccineRecord> getRecordList() {
        return recordList;
    }

    public VaccineRecord getRecord(int index){
        return recordList.get(index);
    }

    public void setRecordList(MyLinkedList<VaccineRecord> vaccineRecords) {
        this.recordList = vaccineRecords;

    }

    public String getPpsNumber() {
        return ppsNumber;
    }

    public void setPpsNumber(String ppsNumber) {
        if (Utilities.validPPS(ppsNumber)) {
            this.ppsNumber = ppsNumber;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(Utilities.validEmail(email)) {
            this.email = email;
        }
    }

    public String getWheelchairUser() {
        return wheelchairUser;
    }

    public void setWheelchairUser(String wheelchairUser) {
        this.wheelchairUser = wheelchairUser;
    }

    @Override
    public String toString() {
        return
                "PPS Number: " + ppsNumber + " Name: " + firstName + " " + lastName + " Date of Birth: " + dateOfBirth + " Phone: " + phone + " Email: " + email + " Wheelchair: " + wheelchairUser;

    }

}

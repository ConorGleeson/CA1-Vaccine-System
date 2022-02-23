package com.example.ca1;


import java.util.Objects;

public class VaccineBooth {
    private String identifier;
    private String floorLevel;
    private String wheelchairAccessible;
    private String centreName;
    private MyLinkedList<Appointment> appointmentsList;


    public VaccineBooth( String centreName, String identifier, String floorLevel, String wheelchairAccessible) {
        this.centreName = centreName;
        this.identifier = identifier;
        this.floorLevel = floorLevel;
        this.wheelchairAccessible = wheelchairAccessible;
        this.appointmentsList = new MyLinkedList<>();
    }

    public MyLinkedList<Appointment> getAppointmentsList() {
        return appointmentsList;
    }

    public Appointment getAppointment(int index){
        return  appointmentsList.get(index);
    }

    public String getCentreName(){
        return centreName;
    }

    public void setCentreName(String centreName){
        this.centreName = centreName;

    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(String floorLevel) {
        this.floorLevel = floorLevel;
    }

    public String getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(String wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccineBooth booth = (VaccineBooth) o;
        return Objects.equals(identifier, booth.identifier) && Objects.equals(floorLevel, booth.floorLevel) && Objects.equals(wheelchairAccessible, booth.wheelchairAccessible) && Objects.equals(centreName, booth.centreName) && Objects.equals(appointmentsList, booth.appointmentsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, floorLevel, wheelchairAccessible, centreName, appointmentsList);
    }

    @Override
    public String toString() {
        return   "Centre: " + centreName +  " Booth Identifier: " + identifier + " Floor Level: " + floorLevel + " Wheelchair Accessible: " + wheelchairAccessible;
    }


}

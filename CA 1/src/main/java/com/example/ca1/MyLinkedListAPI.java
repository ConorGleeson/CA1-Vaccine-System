package com.example.ca1;


public  class  MyLinkedListAPI {
    private  MyLinkedList<VaccineCentre> vaccineCentreList;
    private  MyLinkedList<Patient> patientsList;

    //wrapper class so that the linked lists can be stored as non-static and as a result stored using xml

    public MyLinkedListAPI(){
        this.vaccineCentreList = new MyLinkedList<>();
        this.patientsList = new MyLinkedList<>();

    }

        public  MyLinkedList<VaccineCentre> getVaccineCentreList() {
        return vaccineCentreList;
    }

    public  VaccineCentre getVaccineCentre(int index){
        return vaccineCentreList.get(index);
    }

    public  Patient getPatient(int index){
        return patientsList.get(index);
    }


    public void setVaccineCentreList(MyLinkedList<VaccineCentre> centreList) {
        this.vaccineCentreList = centreList;
    }

    public  MyLinkedList<Patient> getPatientsList() {
        return patientsList;
    }

    public void setPatientsList(MyLinkedList<Patient> patientsList1) {
        this.patientsList = patientsList1;
    }
}

package com.example.rk;

import java.util.ArrayList;

public class Account {
    private final String studentName;
    private final String studentID;
    private final String PIN;
    private final ArrayList<Infraction> infractions;


    public Account(String name, String ID, String pin){
        this.studentName = name;
        this.studentID = ID;
        this.PIN = pin;
        this.infractions = new ArrayList<>();
    }
    public String getName(){
        return studentName;
    }
    public String getID(){
        return studentID;
    }
    public void infractionAdd(Infraction infraction){
        infractions.add(infraction);
    }
    public void printInfractions() {
        if (infractions.isEmpty()){
            System.out.println("No Infractions");
            return;
        }
        for (Infraction infraction : infractions) {
            System.out.println(infraction);
        }
    }
    public boolean verifyPin(String pin){
        return pin.equals(PIN);
    }
}
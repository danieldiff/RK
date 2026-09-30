package com.example.rk;

import java.util.ArrayList;

public class Account {
    private final String studentName;
    private final String studentID;
    private final ArrayList<Infraction> infractions;


    public Account(String name, String ID){
        this.studentName = name;
        this.studentID = ID;
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
        for (Infraction infraction : infractions) {
            System.out.println(infraction);
        }
    }
}

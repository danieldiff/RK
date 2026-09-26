package com.example.rk;

import android.os.Build;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class rk {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        rk recordkeeper = new rk();
        recordkeeper.start(scanner);
    }
    public void start(Scanner scanner){
        ArrayList<Account> records = new ArrayList<>();
        while (true) {
            System.out.println("-- CHOOSE DECISION --");
            System.out.println("1: Make Account || 2: Make Infraction and Updated Account Record || 3: Print Account Record || 4: Print all users in record || 5: End Program");

            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println("Chose Decision: " + choice);
            if (!decisionCompress(choice, scanner, records)){
                break;
            }

        }
    }


    public static void createAccount(Scanner scanner, ArrayList<Account> records){
        while (true) {
            System.out.println("-- CREATING ACCOUNT (Input Nothing on Name to END) --");
            System.out.println("Name: ");
            String name = scanner.nextLine();
            if (name.isEmpty()){
                return;
            }
            System.out.println(name);
            System.out.println("StudentID: ");
            String id = scanner.nextLine();
            boolean idUsed = false;
            for (Account account : records) {
                if (account.getID().equals(id)) {
                    idUsed = true;
                    break;
                }
            }
            if (idUsed){
                System.out.println("ID ALREADY USED.");
                continue;
            }
            System.out.println(id);
            System.out.println("4 DIGIT PIN: ");
            String pin = scanner.nextLine();
            if (pin.length()!=4) {
                System.out.println("PIN MUST BE 4 DIGITS.");
                continue;
            }
            System.out.println(pin);
            records.add(new Account(name, id, pin));
            break;
        }
    }
    public static void makeAndAssignInfraction(Scanner scanner, ArrayList<Account> records){
        //noinspection LoopStatementThatDoesntLoop
        while (true) {
            System.out.println("-- MAKING INFRACTION TICKET (Input Nothing on Offense to END) --");
            System.out.println("Offense: ");
            String offense = scanner.nextLine();
            if (offense.isEmpty()){
                return;
            }
            System.out.println(offense);
            System.out.println("Degree of Offense: ");
            int degree = Integer.parseInt(scanner.nextLine());
            System.out.println(degree);
            LocalDateTime time = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                time = LocalDateTime.now();
            }

            System.out.println("-- WHOSE ACCOUNT? (USE ID) --");
            String ID = scanner.nextLine();
            boolean found = false;
            for (Account account : records) {
                if (account.getID().equals(ID)) {
                    account.infractionAdd(new Infraction(offense, degree, time));
                    found = true;
                    System.out.println(ID);
                    break;
                }
            }
            if (!(found)) {
                System.out.println("Account not found!");
            }
            return;
        }
    }
    public static void printAccountInfractions(Scanner scanner, ArrayList<Account> records){
        System.out.println("-- PRINT WHOSE RECORDS? (USE ID) --");
        String ID = scanner.nextLine();
        boolean found = false;
        for (Account account : records) {
            if (account.getID().equals(ID)) {
                System.out.println(ID);
                account.printInfractions();
                found = true;
                break;
            }
        }
        if (!(found)) {
            System.out.println("Account not found!");
        }
    }
    public static void printAllAccounts(ArrayList<Account> records){
        for (Account account : records){
            System.out.println("Name: " + account.getName() + ", ID: " + account.getID());
        }
    }
    public static boolean decisionCompress(int choice, Scanner scanner, ArrayList<Account> records){
        //1 = Make account
        if (choice == 1) {
            createAccount(scanner, records);
        }
        //2 = Make infraction and upload to select account
        if (choice == 2){
            makeAndAssignInfraction(scanner, records);
        }
        //3 = See account's records
        if (choice == 3){
            printAccountInfractions(scanner, records);
        }
        //4 = Prints all users in the record, showing their username and ID
        if (choice == 4) {
            printAllAccounts(records);
        }
        //5 = Logs out
        if (choice == 5) {
            System.out.println("CLOSING PROGRAM");
            return false;
        }
        //6= Edge case
        if (choice <1 || choice > 5) {
            System.out.println("INVALID DECISION");
        }
        return true;
    }
}

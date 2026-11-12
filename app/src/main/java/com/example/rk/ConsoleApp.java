package com.example.rk;

import android.os.Build;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleApp program = new ConsoleApp();
        program.start(scanner);
    }
    public void start(Scanner scanner){
        ArrayList<Account> records = new ArrayList<>();
        HashMap<String, Account> recordsForLogIn = new HashMap<>();

        while (true) {
            System.out.println("-- CHOOSE DECISION --");
            while (true){
                System.out.println("1: Make Account || 2: login");
                int choice1 = Integer.parseInt(scanner.nextLine());
                if (choice1 == 2){
                    Account thisAccount = login(scanner, recordsForLogIn);
                    if (thisAccount == null){
                        continue;
                    }
                    System.out.println("-- CHOOSE DECISION --");
                    while (true){
                        System.out.println("1: Make Infraction and Updated Account Record || 2: Print Account Record || 3: Print all users in record || 4: Log Out");
                        int choice2 = Integer.parseInt(scanner.nextLine());
                        if (!decisionCompress(choice2, scanner, thisAccount, records)){
                            break;
                        }
                    }
                }

                else if (choice1 == 1){
                    createAccount(scanner, records, recordsForLogIn);
                }
            }
        }
    }
    public static Account login(Scanner scanner, HashMap<String, Account> records) {

        System.out.println("-- STUDENT LOGIN --");

        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        // Check whether the account exists
        Account account = records.get(id);

        if (account == null) {
            System.out.println("ACCOUNT NOT FOUND.");
            return null;
        }

        System.out.print("Enter 4-digit PIN: ");
        String pin = scanner.nextLine();

        // Verify the PIN
        if (account.verifyPin(pin)) {
            System.out.println("LOGIN SUCCESSFUL!");
            System.out.println("Welcome, " + account.getName());
            return account;
        }

        System.out.println("INCORRECT PIN.");
        return null;
    }

    public static void createAccount(Scanner scanner, ArrayList<Account> records, HashMap<String, Account> recordsForLogIn){
        while (true) {
            System.out.println("-- CREATING ACCOUNT (Input Nothing on Name to END) --");
            System.out.println("Name: ");
            String name = scanner.nextLine();
            if (name.isEmpty()){
                return;
            }
            System.out.println("StudentID: ");
            String id = scanner.nextLine();
            if (id.isBlank()){
                return;
            }
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
            System.out.println("4 DIGIT PIN: ");
            String pin = scanner.nextLine();
            if (pin.length()!=4) {
                System.out.println("PIN MUST BE 4 DIGITS.");
                continue;
            }
            Account newAccount = new Account(name, id, pin);
            records.add(newAccount);
            recordsForLogIn.put(id, newAccount);
            break;
        }
    }
    public static void makeAndAssignInfraction(Scanner scanner, Account account){
        //noinspection LoopStatementThatDoesntLoop
        while (true) {
            System.out.println("-- MAKING INFRACTION TICKET (Input Nothing on Offense to END) --");
            System.out.println("Offense: ");
            String offense = scanner.nextLine();
            if (offense.isEmpty()){
                return;
            }
            System.out.println("Degree of Offense: ");
            int degree = Integer.parseInt(scanner.nextLine());
            if (degree<0){
                return;
            }
            LocalDateTime time = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                time = LocalDateTime.now();
            }
            account.infractionAdd(new Infraction(offense, degree, time));
            return;
        }
    }
    public static void printAccountInfractions(Scanner scanner, Account account){
        account.printInfractions();
    }
    public static void printAllAccounts(ArrayList<Account> records){
        for (Account account : records){
            System.out.println("Name: " + account.getName() + ", ID: " + account.getID());
        }
    }
    public static boolean decisionCompress(int choice, Scanner scanner, Account account, ArrayList<Account> records){
        //1 = Make account
        if (choice == 1) {
            makeAndAssignInfraction(scanner, account);
        }
        //2 = Make infraction and upload to select account
        if (choice == 2){
            printAccountInfractions(scanner, account);
        }
        //3 = See account's records
        if (choice == 3){
            printAllAccounts(records);
        }
        //4 = Prints all users in the record, showing their username and ID
        if (choice == 4) {
            System.out.println("CLOSING PROGRAM");
            return false;
        }
        //Not 1-4 = Edge case
        if (choice <1 || choice > 4) {
            System.out.println("INVALID DECISION");
        }
        return true;
    }
}
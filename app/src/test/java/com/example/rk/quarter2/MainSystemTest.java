package com.example.rk.quarter2;

import com.example.rk.rk;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class MainSystemTest {

    @Test
    public void testCompleteSystemFlow() {
        // THE VIRTUAL KEYBOARD

        StringBuilder simulatedUserInput = new StringBuilder();

        System.out.println("--- GENERATING SIMULATED USER INPUTS ---");

        // 1. Create an account
        simulatedUserInput.append("1\n");        // Menu choice
        simulatedUserInput.append("John Doe\n"); // Name
        simulatedUserInput.append("1234\n");     // Student ID
        simulatedUserInput.append("0123\n");     // PIN

        // 2. Create and assign an infraction
        simulatedUserInput.append("2\n");        // Menu choice
        simulatedUserInput.append("Late\n");     // Offense
        simulatedUserInput.append("1\n");        // Degree
        simulatedUserInput.append("1234\n");     // Account ID

        // 3. Print the account's infractions
        simulatedUserInput.append("3\n");        // Menu choice
        simulatedUserInput.append("1234\n");     // Account ID

        // 4. Print all accounts
        simulatedUserInput.append("4\n");        // Menu choice

        // 5. Exit the program
        simulatedUserInput.append("5\n");

        // Convert simulated input into a Scanner
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(
                        simulatedUserInput.toString().getBytes()
                );

        Scanner scanner = new Scanner(inputStream);

        // Run the Recordkeeper program
        rk mainSystem = new rk();
        mainSystem.start(scanner);
    }
}

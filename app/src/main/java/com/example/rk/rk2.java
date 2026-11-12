package com.example.rk;

import android.os.Build;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Application logic for Recordkeeper.
 * This class does not read from the console or depend on Android UI classes.
 */
public class rk2 {

    // One shared instance keeps accounts available while moving between Activities.
    private static final rk2 INSTANCE = new rk2();

    private final Map<String, Account> accounts = new HashMap<>();

    public static rk2 getInstance() {
        return INSTANCE;
    }

    // Private constructor encourages use of the shared application instance.
    private rk2() {
    }

    /** Creates an account. Returns false if input is invalid or the ID is already used. */
    public boolean createAccount(String name, String id, String pin) {
        if (name == null || name.trim().isEmpty()
                || id == null || id.trim().isEmpty()
                || pin == null || !pin.matches("\\d{4}")) {
            return false;
        }

        name = name.trim();
        id = id.trim();

        if (accounts.containsKey(id)) {
            return false;
        }

        Account newAccount = new Account(name, id, pin);
        accounts.put(id, newAccount);
        return true;
    }

    /** Returns the matching account, or null when the ID/PIN combination is invalid. */
    public Account login(String id, String pin) {
        if (id == null || pin == null) {
            return null;
        }

        Account account = accounts.get(id.trim());
        if (account != null && account.verifyPin(pin)) {
            return account;
        }
        return null;
    }

    public Account getAccount(String id) {
        if (id == null) {
            return null;
        }
        return accounts.get(id.trim());
    }

    public ArrayList<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public boolean addInfraction(Account account, String offense, int degree) {
        if (account == null || offense == null || offense.trim().isEmpty() || degree < 0) {
            return false;
        }

        // Only add infractions to accounts managed by this RK instance.
        if (accounts.get(account.getID()) != account) {
            return false;
        }
        LocalDateTime time = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            time = LocalDateTime.now();
        }
        account.infractionAdd(new Infraction(offense.trim(), degree, time));
        return true;
    }
}

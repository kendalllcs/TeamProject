package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * The `BankAccountManager` class provides static methods for basic banking operations,
 * including creating accounts, depositing, withdrawing, and checking balances.
 */
public class BankAccountManager {

    // Map to store account numbers and their corresponding balances
    private static Map<Integer, Integer> accounts = new HashMap<>();
    // Counter to generate unique account numbers
    private static int accountCounter = 1;

    /**
     * Enumeration for operation statuses.
     */
    public enum OperationStatus {
        SUCCESS,
        INSUFFICIENT_FUNDS,
        ACCOUNT_NOT_FOUND,
        INVALID_INPUT
    }

    /**
     * Creates a new account with the specified initial balance.
     *
     * @param initialBalance The initial balance of the new account.
     * @return OperationStatus indicating SUCCESS or INVALID_INPUT.
     */
    public static OperationStatus createAccount(int initialBalance) {
        if (initialBalance < 0) {
            return OperationStatus.INVALID_INPUT;
        }
        accounts.put(accountCounter++, initialBalance);
        return OperationStatus.SUCCESS;
    }

    /**
     * Deposits the specified amount into the given account.
     *
     * @param accountNumber The account number.
     * @param depositAmount The amount to deposit.
     * @return OperationStatus indicating SUCCESS, ACCOUNT_NOT_FOUND, or INVALID_INPUT.
     */
    public static OperationStatus deposit(int accountNumber, int depositAmount) {
        if (depositAmount <= 0) {
            return OperationStatus.INVALID_INPUT;
        }
        if (!accounts.containsKey(accountNumber)) {
            return OperationStatus.ACCOUNT_NOT_FOUND;
        }
        accounts.put(accountNumber, accounts.get(accountNumber) + depositAmount);
        return OperationStatus.SUCCESS;
    }

    /**
     * Withdraws the specified amount from the given account if sufficient funds are available.
     *
     * @param accountNumber  The account number.
     * @param withdrawAmount The amount to withdraw.
     * @return OperationStatus indicating SUCCESS, INSUFFICIENT_FUNDS, ACCOUNT_NOT_FOUND, or INVALID_INPUT.
     */
    public static OperationStatus withdraw(int accountNumber, int withdrawAmount) {
        if (withdrawAmount <= 0) {
            return OperationStatus.INVALID_INPUT;
        }
        if (!accounts.containsKey(accountNumber)) {
            return OperationStatus.ACCOUNT_NOT_FOUND;
        }
        int currentBalance = accounts.get(accountNumber);
        if (withdrawAmount > currentBalance) {
            return OperationStatus.INSUFFICIENT_FUNDS;
        }
        accounts.put(accountNumber, currentBalance - withdrawAmount);
        return OperationStatus.SUCCESS;
    }

    /**
     * Returns the balance of the given account or -1 if the account does not exist.
     *
     * @param accountNumber The account number.
     * @return The balance of the account, or -1 if the account does not exist.
     */
    public static int checkBalance(int accountNumber) {
        return accounts.getOrDefault(accountNumber, -1);
    }

    // Methods for testing purposes (package-private access)

    static void resetAccounts() {
        accounts.clear();
        accountCounter = 1;
    }

    static Map<Integer, Integer> getAccounts() {
        return accounts;
    }

    static int getAccountCounter() {
        return accountCounter;
    }
}
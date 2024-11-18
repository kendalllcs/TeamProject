package com.example;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for `BankAccountManager` using TestNG.
 */
public class BankAccountManagerTest {

    @BeforeMethod
    public void setUp() {
        // Reset the accounts and account counter before each test
        BankAccountManager.resetAccounts();
    }

    // Create Account Tests

    @Test
    public void testCreateAccount_Valid() {
        Assert.assertEquals(BankAccountManager.createAccount(100), BankAccountManager.OperationStatus.SUCCESS);
    }

    @Test
    public void testCreateAccount_Invalid_NegativeBalance() {
        Assert.assertEquals(BankAccountManager.createAccount(-10), BankAccountManager.OperationStatus.INVALID_INPUT);
    }

    // Deposit Tests

    @Test
    public void testDeposit_Valid() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.deposit(1, 50), BankAccountManager.OperationStatus.SUCCESS);
    }

    @Test
    public void testDeposit_InvalidAccount() {
        Assert.assertEquals(BankAccountManager.deposit(99, 50), BankAccountManager.OperationStatus.ACCOUNT_NOT_FOUND);
    }

    @Test
    public void testDeposit_ZeroAmount() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.deposit(1, 0), BankAccountManager.OperationStatus.INVALID_INPUT);
    }

    @Test
    public void testDeposit_NegativeAmount() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.deposit(1, -50), BankAccountManager.OperationStatus.INVALID_INPUT);
    }

    @Test
    public void testDeposit_MaxIntAmount() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.deposit(1, Integer.MAX_VALUE), BankAccountManager.OperationStatus.SUCCESS);
    }

    // Withdraw Tests

    @Test
    public void testWithdraw_Valid() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.withdraw(1, 50), BankAccountManager.OperationStatus.SUCCESS);
    }

    @Test
    public void testWithdraw_InsufficientFunds() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.withdraw(1, 150), BankAccountManager.OperationStatus.INSUFFICIENT_FUNDS);
    }

    @Test
    public void testWithdraw_ExactBalance() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.withdraw(1, 100), BankAccountManager.OperationStatus.SUCCESS);
    }

    @Test
    public void testWithdraw_OverBalanceByOne() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.withdraw(1, 101), BankAccountManager.OperationStatus.INSUFFICIENT_FUNDS);
    }

    @Test
    public void testWithdraw_ZeroAmount() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.withdraw(1, 0), BankAccountManager.OperationStatus.INVALID_INPUT);
    }

    @Test
    public void testWithdraw_NegativeAmount() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.withdraw(1, -50), BankAccountManager.OperationStatus.INVALID_INPUT);
    }

    @Test
    public void testWithdraw_NonExistentAccount() {
        Assert.assertEquals(BankAccountManager.withdraw(99, 50), BankAccountManager.OperationStatus.ACCOUNT_NOT_FOUND);
    }

    // Check Balance Tests

    @Test
    public void testCheckBalance_Valid() {
        BankAccountManager.createAccount(100);
        Assert.assertEquals(BankAccountManager.checkBalance(1), 100);
    }

    @Test
    public void testCheckBalance_NonExistentAccount() {
        Assert.assertEquals(BankAccountManager.checkBalance(99), -1);
    }

    @Test
    public void testCheckBalance_ZeroBalance() {
        BankAccountManager.createAccount(0);
        Assert.assertEquals(BankAccountManager.checkBalance(1), 0);
    }
}

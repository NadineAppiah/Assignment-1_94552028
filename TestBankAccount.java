public class TestBankAccount {
    public static void main(String[] args) {

        //zero account balance
        BankAccount currentAccount = new BankAccount("CA111", AccountType.CURRENT);
        BankAccount savingsAccount = new BankAccount("SA111", AccountType.SAVINGS);

        //account details
        //Current account
        System.out.println("Current Account ID: " + currentAccount.getAcctID());
        System.out.println("Current Account Balance: " + currentAccount.getBalance());
        System.out.println("Current Account Minimum Balance: " + currentAccount.getMinBalance());

        //Savings account
        System.out.println("Savings Account ID: " + savingsAccount.getAcctID());
        System.out.println("Savings Account Balance: " + savingsAccount.getBalance());
        System.out.println("Savings Account Minimum Balance: " + savingsAccount.getMinBalance());

        //opening account balance
        BankAccount currentAccount2 = new BankAccount(AccountType.CURRENT, "CA211", 500.0);
        BankAccount savingsAccount2 = new BankAccount(AccountType.SAVINGS, "SA311", 500.0);

        System.out.println("Current Account Balance: " + currentAccount2.getBalance());
        System.out.println("Savings Account Balance: " + savingsAccount2.getBalance());

        //deposit
        System.out.println("Savings account balance: " + savingsAccount2.getBalance());
        savingsAccount2.deposit(230.00);
        System.out.println("Savings account balance after depositing 200: " + savingsAccount2.getBalance());

        //withdrawal
        System.out.println("Current account balance: " + currentAccount2.getBalance());
        boolean Success = currentAccount2.withdraw(170.00);
        System.out.println("Withdrawal Successful: " + Success);
        System.out.println("Current account balance after withdrawing 170: " + currentAccount2.getBalance());

        //testing other withdrawal conditions
        //withdrawal insufficient balance
        System.out.println("Current account balance: " + currentAccount2.getBalance());
        System.out.println("Current account minimum balance: " + currentAccount2.getMinBalance());
        Success = currentAccount2.withdraw(660.00);
        System.out.println("Withdrawal Successful: " + Success);
        System.out.println("Current account balance after withdrawing 660: " + currentAccount2.getBalance());

        //withdrawal with savings account withdrawal limit
        System.out.println("Savings account balance: " + savingsAccount2.getBalance());
        System.out.println("First withdrawal");
        savingsAccount2.withdraw(110.00);
        System.out.println("Savings account balance after withdrawing 110.00: " + savingsAccount2.getBalance());

        System.out.println("Second withdrawal");
        savingsAccount2.withdraw(50.00);
        System.out.println("Savings account balance after withdrawing 50.00: " + savingsAccount2.getBalance());

        System.out.println("Third withdrawal");
        savingsAccount2.withdraw(70.00);
        System.out.println("Savings account balance after withdrawing 70.00: " + savingsAccount2.getBalance());

        //withdrawal with current account withdrawal limit
        BankAccount currentAccount3 = new BankAccount(AccountType.CURRENT, "CA311", 5650.00 );
        System.out.println("Current account balance " + currentAccount3.getBalance());

        for (int i = 1; i <= 6; i++) {
            Success = currentAccount3.withdraw(210.00);
            System.out.println("Withdrawal Success: " + Success);
            System.out.println("Current account balance: " + currentAccount3.getBalance());
        }
        //monthly maintenance fee and Interest rate
        //Current account
        BankAccount currentAccount4 = new BankAccount(AccountType.CURRENT, "CA411", 1200.00 );
        System.out.println("Current account balance: " + currentAccount4.getBalance());
        currentAccount4.performMonthlyMaintenance();

        BankAccount savingsAccount3 = new BankAccount(AccountType.SAVINGS, "SA311", 1000.00 );
        System.out.println("Savings account balance: " + savingsAccount3.getBalance());
        savingsAccount3.performMonthlyMaintenance();

        //transfer
        BankAccount account1 = new BankAccount(AccountType.CURRENT, "A111", 700.00);
        BankAccount account2 = new BankAccount(AccountType.CURRENT, "A211", 500.00);

        System.out.println("Account 1 balance before transfer: " + account1.getBalance());
        System.out.println("Account 2 balance before transfer: " + account2.getBalance());

        Success = account1.transfer(true, account2, 100.00);
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());

        Success = account1.transfer(false, account2, 100.0);
        System.out.println("Transfer successful: " + Success);
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());

        //transfer test 2
        System.out.println("Account 1 balance before transfer: " + account1.getBalance());
        System.out.println("Account 2 balance before transfer: " + account2.getBalance());

        Success = account1.transfer(false, account2, 200.0);
        System.out.println("Transfer successful: " + Success);
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());

        //transfer test 3
        System.out.println("Account 1 balance before transfer: " + account1.getBalance());
        System.out.println("Account 2 balance before transfer: " + account2.getBalance());

        Success = account1.transfer(true, account2, 1000.0);
        System.out.println("Transfer successful: " + Success);
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());

        //withdrawal after maintenance fee
        BankAccount savingsAccount4 = new BankAccount(AccountType.SAVINGS, "SA411", 500.0);
        System.out.println("Making 2 withdrawals: ");
        savingsAccount4.withdraw(50.0);
        savingsAccount4.withdraw(50.0);
        System.out.println("Attempting 3rd withdrawal: ");
        savingsAccount4.withdraw(50.0);

        System.out.println("Performing monthly maintenance: ");
        savingsAccount4.performMonthlyMaintenance();

        System.out.println("After maintenance, attempting withdrawal: ");
        Success = savingsAccount4.withdraw(50.0);
        System.out.println("Withdrawal successful: " + Success);
        System.out.println("Balance: " + savingsAccount4.getBalance());

    }

}

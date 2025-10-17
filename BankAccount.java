public class BankAccount {

    //Instantiating variables
    private AccountType acctType;
    private String acctID;
    private double balance;
    private int numWithdrawals;
    private boolean inTheRed;
    private double minBalance;
    private double maintenanceFee;
    private double interestRate;
    private int withdrawalLimit;

    private double CURRENT_ACCT_MIN_BALANCE = 50.0;
    private double SAVINGS_ACCT_MIN_BALANCE = 100.0;
    private double CURRENT_ACCT_MAINTENANCE_FEE = 8.0;
    private double SAVINGS_ACCT_INTEREST_RATE = 0.07;
    private final double SAVINGS_WITHDRAWAL_LIMIT = 2;
    private final double CURRENT_WITHDRAWAL_LIMIT = -1;

    //Ist constructor
    public BankAccount(String id, AccountType type){
        this.acctType = type;
        this.acctID = id;
        this.numWithdrawals = 0;
        this.balance = 0;

        if (type == AccountType.CURRENT) {
            this.minBalance = CURRENT_ACCT_MIN_BALANCE;
            this.interestRate = 0;
            this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            this.withdrawalLimit = (int) CURRENT_WITHDRAWAL_LIMIT;
        }else {
            this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
            this.maintenanceFee = 0;
            this.withdrawalLimit = (int) SAVINGS_WITHDRAWAL_LIMIT;
            this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
        }
        this.inTheRed = (this.balance < this.minBalance);
    }

    //2nd Constructor
    public BankAccount(AccountType type, String id, double openingBalance) {
        this.balance = openingBalance;
        this.acctType = type;
        this.acctID = id;
        this.numWithdrawals = 0;

        if (type == AccountType.CURRENT) {
            this.minBalance = CURRENT_ACCT_MIN_BALANCE;
            this.interestRate = 0;
            this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            this.withdrawalLimit = (int) CURRENT_WITHDRAWAL_LIMIT;
        }else {
            this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
            this.maintenanceFee = 0;
            this.withdrawalLimit = (int) SAVINGS_WITHDRAWAL_LIMIT;
            this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
        }
        this.inTheRed = (this.balance < this.minBalance);
    }

    //getter methods
    public double getMinBalance() {
        return minBalance;
    }

    public String getAcctID() {
        return acctID;
    }

    public AccountType getAcctType() {
        return acctType;
    }

    public double getBalance() {
        return balance;
    }

    //withdrawal method
    public boolean withdraw(double amount) {
        double transaction = balance - amount;

        if (inTheRed) {
            System.out.println("Withdrawal was not successful. Insufficient balance");
            return false;
        } else if (numWithdrawals >= withdrawalLimit && withdrawalLimit != -1) {
            System.out.println("Withdrawal was not successful. Insufficient balance");
            return false;
        } else if (transaction < minBalance) {
            System.out.println("Withdrawal was not successful. Insufficient balance");
            return false;
        } else {
            balance -= amount;
            numWithdrawals++;
            return true;
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void performMonthlyMaintenance() {
        double earnedInterest = interestRate/12 * balance;
        balance += earnedInterest;
        balance -= maintenanceFee;
        double newBalance = balance;
        inTheRed = balance < minBalance;

        if (inTheRed) {
            System.out.println("WARNING: This account is in the red");
            numWithdrawals = 0;
        }

        System.out.println("Earned interest: " + "<" + earnedInterest + ">");
        System.out.println("Maintenance fee: " + "<" + maintenanceFee + ">");
        System.out.println("Updated balance: " + "<" + newBalance + ">");
    }
    public boolean transfer(boolean transferTo, BankAccount otherAccount, double amount) {
        if (transferTo) {
            //transferring from this account to other account
            if (this.withdraw(amount)) {
                otherAccount.deposit(amount);
                return true;
            }else {
                return false;
            }
        } else {
            //transferring from other account to this account
            if(otherAccount.withdraw(amount)) {
                deposit(amount);
                return true;
            } else {
                return false;
            }
        }

    }

}

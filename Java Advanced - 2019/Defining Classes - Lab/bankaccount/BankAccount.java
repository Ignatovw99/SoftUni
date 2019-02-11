package bankaccount;

public class BankAccount {
    private final static double DEFAULT_INTEREST_RATE = 0.02;

    private static double interestRate = DEFAULT_INTEREST_RATE;
    private static int accountsCount = 0;

    private int id;
    private double balance;

    public BankAccount() {
        accountsCount++;
        this.id = accountsCount;
    }

    public int getId() {
        return this.id;
    }

    public static void setInterestRate(double interest) {
        BankAccount.interestRate = interest;
    }

    public double getInterest(int years) {
        return BankAccount.interestRate * this.balance * years;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}

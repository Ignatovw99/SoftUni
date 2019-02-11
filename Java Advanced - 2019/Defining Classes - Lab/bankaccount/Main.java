package bankaccount;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<Integer, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] lineArguments = scanner.nextLine().split("\\s+");
        String command = lineArguments[0];

        while (!"End".equals(command)) {
            switch (command) {
                case "Create":
                    createBankAccount();
                    break;
                case "Deposit":
                    depositSum(Integer.parseInt(lineArguments[1]), Double.parseDouble(lineArguments[2]));
                    break;
                case "SetInterest":
                    setInterest(Double.parseDouble(lineArguments[1]));
                    break;
                case "GetInterest":
                    getInterest(Integer.parseInt(lineArguments[1]), Integer.parseInt(lineArguments[2]));
                    break;
            }

            lineArguments = scanner.nextLine().split("\\s+");
            command = lineArguments[0];
        }
    }

    private static void getInterest(int id, int years) {
        BankAccount account = accounts.get(id);

        if (account != null) {
            System.out.printf("%.2f%n", account.getInterest(years));
        } else {
            System.out.println("Account does not exist");
        }
    }

    private static void setInterest(double interest) {
        BankAccount.setInterestRate(interest);
    }

    private static void depositSum(int id, double amount) {
        BankAccount bankAccount = accounts.get(id);

        if (bankAccount == null) {
            System.out.println("Account does not exist");
            return;
        }

        bankAccount.deposit(amount);
        System.out.println(String.format("Deposited %.0f to ID%d", amount, id));
    }

    private static void createBankAccount() {
        BankAccount bankAccount = new BankAccount();
        accounts.put(bankAccount.getId(), bankAccount);

        System.out.println("Account ID" + bankAccount.getId() + " created");
    }
}

package pl.michal_iwanus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BankAccount Account = new BankAccount();
        ModerateBankAccount ModerateAccount = new ModerateBankAccount();
        AdminBankAccount AdminAccount = new AdminBankAccount();

        do {
            Account.loginBankAccount();
        } while (!Account.logged);

        do {
            switch (Account.getAccess()) {
                case 0 -> {
                    Account.menuForCustomer();
                    switch (Account.choice) {
                        case '1' -> Account.getBalance();
                        case '2' -> Account.withdrawal();
                        case '3' -> Account.deposit();
                        case '4' -> System.exit(0);
                    }
                }
                case 1 -> {
                    Account.menuForModerate();
                    switch (Account.choice) {
                        case '1' -> ModerateAccount.getBalance();
                        case '2' -> Account.getBalance();
                        case '3' -> ModerateAccount.withdrawal();
                        case '4' -> ModerateAccount.deposit();
                        case '5' -> System.exit(0);
                    }
                }
                case 2 -> {
                    Account.menuForAdmin();
                    switch (Account.choice) {
                        case '1' -> AdminAccount.getBalance();
                        case '2' -> Account.getBalance();
                        case '3' -> ModerateAccount.getBalance();
                        case '4' -> AdminAccount.withdrawal();
                        case '5' -> AdminAccount.deposit();
                        case '6' -> System.exit(0);
                    }
                }
                default -> System.out.println("Missing information.");
            }
        } while (true);
    }
}


class AdminBankAccount
{
    Scanner Scanner = new Scanner(System.in);
    String admin_login = "admin";
    String admin_password = "1234";
    private double balance = 11421.22;

    void getBalance()
    {
        System.out.println("Balance is: " + BigDecimal.valueOf(balance).setScale(2,
                RoundingMode.HALF_UP).doubleValue());
    }

    void withdrawal()
    {
        System.out.print("Set money to withdrawal: ");
        double withdrawal = Scanner.nextDouble();

        if(balance >= withdrawal) {
            balance -= withdrawal;
            System.out.println("Withdrawal succeed. New balance is: " + balance);
        }
        else {
            System.out.println("Not enough money to withdrawal.");
        }
    }

    void deposit()
    {
        System.out.print("Set money to deposit: ");
        double deposit = Scanner.nextDouble();
        balance += deposit;
        System.out.println("Deposit succeed. Your new balance is: " + balance);
    }
}

class ModerateBankAccount
{
    Scanner Scanner = new Scanner(System.in);
    String moderate_login = "moderate";
    String moderate_password = "1234";
    private double balance = 1421.98;

    void getBalance()
    {
        System.out.println("Balance is: " + BigDecimal.valueOf(balance).setScale(2,
                RoundingMode.HALF_UP).doubleValue());
    }

    void withdrawal()
    {
        System.out.print("Set money to withdrawal: ");
        double withdrawal = Scanner.nextDouble();

        if(balance >= withdrawal) {
            balance -= withdrawal;
            System.out.println("Withdrawal succeed. New balance is: " + balance);
        }
        else {
            System.out.println("Not enough money to withdrawal.");
        }
    }

    void deposit()
    {
        System.out.print("Set money to deposit: ");
        double deposit = Scanner.nextDouble();
        balance += deposit;
        System.out.println("Deposit succeed. Your new balance is: " + balance);
    }
}

class BankAccount
{
    AdminBankAccount AdminBankAccount = new AdminBankAccount();
    ModerateBankAccount ModerateBankAccount = new ModerateBankAccount();
    Scanner Scanner = new Scanner(System.in);
    private double balance = 300.12;
    private int access;
    boolean logged = false;
    char choice;

    String customer_login = "customer";
    String customer_password = "1234";
    String loginAttempt;
    String passwordAttempt;

    void loginBankAccount()
    {
        System.out.print("Enter you login: ");
        loginAttempt = Scanner.nextLine();

        System.out.print("Enter you password: ");
        passwordAttempt = Scanner.nextLine();

        if ((Objects.equals(loginAttempt, customer_login)) &&
            (Objects.equals(passwordAttempt, customer_password)))
        {
            access = 0;
            logged = true;
        }
        else if ((Objects.equals(loginAttempt, ModerateBankAccount.moderate_login)) &&
                (Objects.equals(passwordAttempt, ModerateBankAccount.moderate_password)))
        {
            access = 1;
            logged = true;
        }else if ((Objects.equals(loginAttempt, AdminBankAccount.admin_login)) &&
                (Objects.equals(passwordAttempt, AdminBankAccount.admin_password)))
        {
            access = 2;
            logged = true;
        }
        else
        {
            System.out.println("Access denied. Failed login or password.");
        }
    }

    void menuForCustomer()
    {
        System.out.println("Logged as Customer");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("What do you want to do?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1 - Get balance of my account");
        System.out.println("2 - Withdrawal money");
        System.out.println("3 - Deposit money");
        System.out.println("4 - Exit");
        choice = Scanner.next().charAt(0);
    }
    void menuForModerate()
    {
        System.out.println("Logged as Moderate");
        System.out.println("What do you want to do?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1 - Get balance of my account");
        System.out.println("2 - Get balance of customer account");
        System.out.println("3 - Withdrawal money");
        System.out.println("4 - Deposit money");
        System.out.println("5 - Exit");
        choice = Scanner.next().charAt(0);
    }
    void menuForAdmin()
    {
        System.out.println("Logged as Admin");
        System.out.println("What do you want to do?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1 - Get balance of my account");
        System.out.println("2 - Get balance of customer account");
        System.out.println("3 - Get balance of moderate account");
        System.out.println("4 - Withdrawal money");
        System.out.println("5 - Deposit money");
        System.out.println("6 - Exit");
        choice = Scanner.next().charAt(0);
    }

    void getBalance()
    {
        System.out.println("Balance is: " + BigDecimal.valueOf(balance).setScale(2,
                RoundingMode.HALF_UP).doubleValue());
    }


    int getAccess()
    {
        return switch (access) {
            case 1 -> 1;
            case 2 -> 2;
            default -> 0;
        };
    }

    void withdrawal()
    {
        if (access == 0)
        {
            System.out.print("Set money to withdrawal: ");
            double withdrawal = Scanner.nextDouble();

            if(balance >= withdrawal)
            {
                balance -= withdrawal;
                System.out.println("Withdrawal succeed. New balance is: " + balance);
            }
            else
            {
                System.out.println("Not enough money to withdrawal.");
            }
        }
    }

    void deposit()
    {
        System.out.print("Set money to deposit: ");
        double deposit = Scanner.nextDouble();
        balance += deposit;
        System.out.println("Deposit succeed. Your new balance is: " + balance);
    }
}
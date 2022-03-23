package pl.michal_iwanus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Scanner;
/*
    This program is simulating of simple project to logging and operate in settings of Bank Account
    Customer can see his own setting, but he cannot see setting of moderate and admin
    Moderate can see his own setting and customer, but he cannot see setting of admin
    Admin can see his own setting, moderate and customer
    Access is denied in level 0, 1 and 2
    Access level 0 is customer
    Access level 1 is moderate
    Access level 2 is admin
 */
public class Main {

    public static void main(String[] args) {
        //Creating constructor
        BankAccount Account = new BankAccount();
        ModerateBankAccount ModerateAccount = new ModerateBankAccount();
        AdminBankAccount AdminAccount = new AdminBankAccount();

        //Loop for logging
        do {
            Account.loginBankAccount();
        } while (!Account.logged);

        //Classic menu
        do {
            //Access shall be granted by previous loop
            //Menu is change by level of access
            switch (Account.getAccess()) {
                case 0 -> {
                    Account.menuForCustomer();
                    switch (Account.choice) {
                        case '1' -> Account.getBalance(); //Get balance of customer account
                        case '2' -> Account.withdrawal(); //Withdrawal money from customer account
                        case '3' -> Account.deposit();    //Deposit money to customer account
                        case '4' -> System.exit(0); //Exit from program
                    }
                }
                case 1 -> {
                    Account.menuForModerate();
                    switch (Account.choice) {
                        case '1' -> ModerateAccount.getBalance();     //Get balance of moderate account
                        case '2' -> Account.getBalance();             //Show balance of customer account
                        case '3' -> ModerateAccount.withdrawal();     //Withdrawal money from moderate account
                        case '4' -> ModerateAccount.deposit();        //Deposit money to moderate account
                        case '5' -> System.exit(0);             //Exit from program
                    }
                }
                case 2 -> {
                    Account.menuForAdmin();
                    switch (Account.choice) {
                        case '1' -> AdminAccount.getBalance();      //Get balance of admin account
                        case '2' -> Account.getBalance();           //Show balance of customer account
                        case '3' -> ModerateAccount.getBalance();   //Show balance of moderate account
                        case '4' -> AdminAccount.withdrawal();      //Withdrawal money from admin account
                        case '5' -> AdminAccount.deposit();         //Deposit money to admin account
                        case '6' -> System.exit(0);           //Exit from program
                    }
                }
                default ->
                        {
                            //System errors when access is different between access lvl 0, 1 and 2
                            System.out.println("Access is not recognised");
                            System.exit(1);
                        }
            }
        } while (true);
    }
}

//Class for admin account
class AdminBankAccount
{
    Scanner Scanner = new Scanner(System.in);
    String admin_login = "admin";
    String admin_password = "1234";
    private double balance = 11421.22;

    //Function for get balance of admin account
    void getBalance()
    {
        System.out.println("Balance is: " + BigDecimal.valueOf(balance).setScale(2,
                RoundingMode.HALF_UP).doubleValue());
    }

    //Function for withdrawal money from admin account
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

    //Function for deposit money from admin account
    void deposit()
    {
        System.out.print("Set money to deposit: ");
        double deposit = Scanner.nextDouble();
        balance += deposit;
        System.out.println("Deposit succeed. Your new balance is: " + balance);
    }
}

//class for moderate account
class ModerateBankAccount
{
    Scanner Scanner = new Scanner(System.in);
    String moderate_login = "moderate";
    String moderate_password = "1234";
    private double balance = 1421.98;

    //Function for get balance of moderate account
    void getBalance()
    {
        System.out.println("Balance is: " + BigDecimal.valueOf(balance).setScale(2,
                RoundingMode.HALF_UP).doubleValue());
    }

    //Function for withdrawal money from moderate account
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

    //Function for deposit money from moderate account
    void deposit()
    {
        System.out.print("Set money to deposit: ");
        double deposit = Scanner.nextDouble();
        balance += deposit;
        System.out.println("Deposit succeed. Your new balance is: " + balance);
    }
}

//Class for Customer account and operate for customer, moderate and admin menu, logging attempt
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

    //Logging system
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

    //Customers menu
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
    //Moderates menu
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
    //Admins menu
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

    //Function for get balance of customer account
    void getBalance()
    {
        System.out.println("Balance is: " + BigDecimal.valueOf(balance).setScale(2,
                RoundingMode.HALF_UP).doubleValue());
    }


    //Function to get access for menu
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
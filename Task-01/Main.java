import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = Math.max(initialDeposit, 0.0);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Error: Insufficient balance. Current balance is $" + balance);
        } else {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            System.out.println("Remaining Balance: $" + balance);
        }
    }

    public void displayAccountInfo() {
        System.out.println("\nAccount Details:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Current Balance: $" + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        System.out.println("BANK MANAGEMENT SYSTEM");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice (1-5): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    if (account != null) {
                        System.out.println("Account already exists for this session!");
                        break;
                    }
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine().trim();
                    
                    System.out.print("Enter Account Holder Name: ");
                    String accHolder = scanner.nextLine().trim();

                    System.out.print("Enter Initial Deposit Amount: ");
                    double initialDeposit = 0;
                    try {
                        initialDeposit = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount entered. Setting initial deposit to 0.");
                    }

                    account = new BankAccount(accNum, accHolder, initialDeposit);
                    System.out.println("Account created successfully!");
                    break;

                case 2:
                    if (account == null) {
                        System.out.println("Please create an account first.");
                    } else {
                        System.out.print("Enter amount to deposit: ");
                        try {
                            double amount = Double.parseDouble(scanner.nextLine());
                            account.deposit(amount);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid numerical amount.");
                        }
                    }
                    break;

                case 3:
                    if (account == null) {
                        System.out.println("Please create an account first.");
                    } else {
                        System.out.print("Enter amount to withdraw: ");
                        try {
                            double amount = Double.parseDouble(scanner.nextLine());
                            account.withdraw(amount);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid numerical amount.");
                        }
                    }
                    break;

                case 4:
                    if (account == null) {
                        System.out.println("Please create an account first.");
                    } else {
                        account.displayAccountInfo();
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using our banking system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 5.");
            }
        }
    }
}

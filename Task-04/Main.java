import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }

    public void displayEmployee() {
        System.out.println("ID: " + id + " | Name: " + name + " | Department: " + department + " | Salary: $" + salary);
    }
}

public class Main {
    private static List<Employee> employeeList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nEMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: viewEmployees(); break;
                case 3: searchEmployee(); break;
                case 4: updateEmployee(); break;
                case 5: deleteEmployee(); break;
                case 6:
                    System.out.println("Exiting system. Have a great day!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 6.");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return;
        }

        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                System.out.println("Error: Employee with ID " + id + " already exists.");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine().trim();

        System.out.print("Enter Salary: ");
        double salary;
        try {
            salary = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary format.");
            return;
        }

        employeeList.add(new Employee(id, name, department, salary));
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }
        System.out.println("\nEmployee List:");
        for (Employee emp : employeeList) {
            emp.displayEmployee();
        }
    }

    private static void searchEmployee() {
        System.out.print("Enter Name or Department to search: ");
        String query = scanner.nextLine().trim().toLowerCase();
        boolean found = false;

        for (Employee emp : employeeList) {
            if (emp.getName().toLowerCase().contains(query) || emp.getDepartment().toLowerCase().contains(query)) {
                emp.displayEmployee();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching employee records found.");
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return;
        }

        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                System.out.print("Enter New Name (leave blank to keep current): ");
                String newName = scanner.nextLine().trim();
                if (!newName.isEmpty()) emp.setName(newName);

                System.out.print("Enter New Department (leave blank to keep current): ");
                String newDept = scanner.nextLine().trim();
                if (!newDept.isEmpty()) emp.setDepartment(newDept);

                System.out.print("Enter New Salary (enter -1 to keep current): ");
                try {
                    double newSalary = Double.parseDouble(scanner.nextLine());
                    if (newSalary >= 0) emp.setSalary(newSalary);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Salary left unchanged.");
                }

                System.out.println("Employee record updated successfully!");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }

    private static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return;
        }

        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                employeeList.remove(i);
                System.out.println("Employee record deleted successfully!");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }
}

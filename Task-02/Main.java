import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int rollNumber;
    private String name;
    private double marks;

    public Student(int rollNumber, String name, double marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = marks;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void displayStudent() {
        System.out.println("Roll No: " + rollNumber + " | Name: " + name + " | Marks: " + marks);
    }
}

public class Main {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nSTUDENT MANAGEMENT SYSTEM");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Record");
            System.out.println("4. Delete Student Record");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Exiting system. Have a great day!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 5.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Roll Number: ");
        int roll;
        try {
            roll = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number format.");
            return;
        }

        for (Student s : studentList) {
            if (s.getRollNumber() == roll) {
                System.out.println("Error: A student with Roll Number " + roll + " already exists.");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Marks: ");
        double marks;
        try {
            marks = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid marks format.");
            return;
        }

        studentList.add(new Student(roll, name, marks));
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\nList of Students:");
        for (Student s : studentList) {
            s.displayStudent();
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Roll Number of student to update: ");
        int roll;
        try {
            roll = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number format.");
            return;
        }

        for (Student s : studentList) {
            if (s.getRollNumber() == roll) {
                System.out.print("Enter New Name (leave blank to keep current): ");
                String newName = scanner.nextLine().trim();
                if (!newName.isEmpty()) {
                    s.setName(newName);
                }

                System.out.print("Enter New Marks (enter -1 to keep current): ");
                try {
                    double newMarks = Double.parseDouble(scanner.nextLine());
                    if (newMarks >= 0) {
                        s.setMarks(newMarks);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Marks left unchanged.");
                }

                System.out.println("Student record updated successfully!");
                return;
            }
        }
        System.out.println("Error: Student with Roll Number " + roll + " not found.");
    }

    private static void deleteStudent() {
        System.out.print("Enter Roll Number of student to delete: ");
        int roll;
        try {
            roll = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number format.");
            return;
        }

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber() == roll) {
                studentList.remove(i);
                System.out.println("Student record deleted successfully!");
                return;
            }
        }
        System.out.println("Error: Student with Roll Number " + roll + " not found.");
    }
}

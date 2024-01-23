import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected int age;

    public Person(String id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + firstName + " " + lastName + ", Age: " + age;
    }
}

class Student extends Person {
    private List<Course> courses;

    public Student(String id, String firstName, String lastName, int age) {
        super(id, firstName, lastName, age);
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public String toString() {
        return super.toString() + ", Courses: " + courses;
    }
}

class Course {
    private String code;
    private String name;
    private int credits;
    private String grade;

    public Course(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return code + " - " + name + " (" + credits + " credits) - Grade: " + grade;
    }
}

class AdminStaff extends Person {
    private String role;

    public AdminStaff(String id, String firstName, String lastName, int age, String role) {
        super(id, firstName, lastName, age);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return super.toString() + ", Role: " + role;
    }
}

public class StudentInformationSystem {
    private static List<Student> students = new ArrayList<>();
    private static List<AdminStaff> adminStaffList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Information System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Administrative Staff");
            System.out.println("4. View Administrative Staff");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    addAdminStaff(scanner);
                    break;

                case 4:
                    viewAdminStaff();
                    break;

                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.next();
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter last name: ");
        String lastName = scanner.next();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        Student student = new Student(studentId, firstName, lastName, age);

        System.out.println("Adding courses (enter 'done' when finished):");
        while (true) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.next();
            if (courseCode.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter course name: ");
            String courseName = scanner.next();
            System.out.print("Enter credits: ");
            int credits = scanner.nextInt();

            Course course = new Course(courseCode, courseName, credits);
            student.addCourse(course);
        }

        students.add(student);
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        System.out.println("\nList of Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void addAdminStaff(Scanner scanner) {
        System.out.print("Enter staff ID: ");
        String staffId = scanner.next();
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter last name: ");
        String lastName = scanner.next();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter role: ");
        String role = scanner.next();

        AdminStaff adminStaff = new AdminStaff(staffId, firstName, lastName, age, role);
        adminStaffList.add(adminStaff);
        System.out.println("Administrative Staff added successfully!");
    }

    private static void viewAdminStaff() {
        System.out.println("\nList of Administrative Staff:");
        for (AdminStaff adminStaff : adminStaffList) {
            System.out.println(adminStaff);
        }
    }
}

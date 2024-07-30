import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String courseId;
    String title;
    String description;
    int capacity;
    String schedule;
    ArrayList<String> registeredStudents;

    public Course(String courseId, String title, String description, int capacity, String schedule) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public boolean registerStudent(String studentID) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentID);
            return true;
        }
        return false;
    }

    public boolean dropStudent(String studentID) {
        return registeredStudents.remove(studentID);
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    @Override
    public String toString() {
        return "Course Code: " + courseId + "\nTitle: " + title + "\nDescription: " + description +
            "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nAvailable Slots: " + getAvailableSlots();
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.registerStudent(this.studentID)) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for the course: " + course.title);
        } else {
            System.out.println("Failed to register for the course: " + course.title + ". Capacity full.");
        }
    }

    public void dropCourse(Course course) {
        if (course.dropStudent(this.studentID)) {
            registeredCourses.remove(course);
            System.out.println("Successfully dropped the course: " + course.title);
        } else {
            System.out.println("Failed to drop the course: " + course.title + ". Not registered in this course.");
        }
    }

    @Override
    public String toString() {
        StringBuilder courses = new StringBuilder();
        for (Course course : registeredCourses) {
            courses.append(course.title).append("\n");
        }
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses:\n" + courses.toString();
    }
}

public class StudentCourseRegistrationSystem {

    private static HashMap<String, Course> courses = new HashMap<>();
    private static HashMap<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample Courses
        courses.put("CS101", new Course("CS101", "Computer Science Engineering", "DSA", 20, "Mon-Wed 10:00-11:30"));
        courses.put("MA101", new Course("MA101", "Calculus", "Introduction to calculus", 25, "Tue-Thu 12:00-13:30"));

        // Sample Students
        students.put("S001", new Student("S001", "Alice"));
        students.put("S002", new Student("S002", "Bob"));

        while (true) {
            System.out.println("\n1. List Courses\n2. Register for Course\n3. Drop Course\n4. View Student Details\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    viewStudentDetails(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses.values()) {
            System.out.println(course);
            System.out.println();
        }
    }

    private static void registerCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.next();
        Student student = students.get(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter Course Id: ");
        String courseCode = scanner.next();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        student.registerCourse(course);
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.next();
        Student student = students.get(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter Course Id: ");
        String courseCode = scanner.next();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        student.dropCourse(course);
    }

    private static void viewStudentDetails(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.next();
        Student student = students.get(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println(student);
    }
}


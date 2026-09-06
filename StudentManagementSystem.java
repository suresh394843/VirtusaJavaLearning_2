import org.example.Student;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManagementSystem {
    private static Student[] students = new Student[100];
    private static int count = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    public static void run() {

        while (true) {
            System.out.println("\n========================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM        ");
            System.out.println("========================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Remove Student");
            System.out.println("6. Display Students by Department");
            System.out.println("7. Display Top Student");
            System.out.println("8. Display Student Map (HashMap)");
            System.out.println("9. Search Student Using Map");
            System.out.println("10. Display Students Using LinkedHashMap");
            System.out.println("11. Exit");
            System.out.print("Enter option (1-11): ");

            String inputOption = scanner.nextLine().trim();
            if (inputOption.equals("1")) {
                addStudent();
            } else if (inputOption.equals("2")) {
                displayAllStudents();
            } else if (inputOption.equals("3")) {
                searchStudent();
            } else if (inputOption.equals("4")) {
                updateStudent();
            } else if (inputOption.equals("5")) {
                deleteStudent();
            } else if (inputOption.equals("6")) {
                displayStudentByDepartment();
            } else if (inputOption.equals("7")) {
                displayTopStudent();
            } else if (inputOption.equals("8")) {
                displayStudentMap();
            } else if (inputOption.equals("9")) {
                searchStudentUsingMap();
            } else if (inputOption.equals("10")) {
                displayStudentLinkedHashMap();
            } else if (inputOption.equals("11")) {
                System.out.println("Thank you for using the system!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
            System.out.println();

        }
    }

    public static void addStudent() {
        if (count >= students.length) {
            System.out.println("Database is full.");
            return;
        }

        int studentID = -1;
        String studentName = "";
        int age = -1;
        String department = "";
        double marks = -1;

        do {
            System.out.println("Enter Student ID: ");
            String idStr = scanner.nextLine().trim();
            try {
                studentID = Integer.parseInt(idStr);
                if (studentID <= 0) {
                    System.out.println("Invalid Student ID.");
                } else if (findStudentByIndex(studentID) != -1) {
                    System.out.println("Student ID already exists.");
                    studentID = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Student ID.");

            }
        } while (studentID <= 0);

        do {
            System.out.println("Enter Student Name: ");
            studentName = scanner.nextLine().trim();

            if (studentName.isEmpty()) {
                System.out.println("Name cannot be empty.");
            } else {
                break;
            }
        } while (studentName.isEmpty());


        do {
            System.out.println("Enter Age: ");
            String ageStr = scanner.nextLine().trim();
            try {
                age = Integer.parseInt(ageStr);
                if (age <= 0) {
                    System.out.println("Invalid Age.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Age.");

            }
        } while (age <= 0);

        System.out.println("Enter Department: ");
        department = scanner.nextLine().trim();

        do {
            System.out.println("Enter Marks: ");
            String marksStr = scanner.nextLine().trim();
            try {
                marks = Double.parseDouble(marksStr);
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks should be between 0 and 100.");
                    marks = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Marks.");
            }
        } while (marks < 0);
        students[count] = new Student(studentID, studentName, age, department, marks);
        count++;
        System.out.println("Student added successfully.");

    }

    public static void displayAllStudents() {
        if (count == 0) {
            System.out.println("No records found.");
            return;
        }

        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-5s %-15s %-10s\n",
                "ID", "Name", "Age", "Department", "Marks");
        System.out.println("------------------------------------------------------------------");


        for (Student student : students) {
            if (student != null) {
                System.out.printf("%-10d %-15s %-5d %-15s %-10.1f\n",
                        student.getStudentID(),
                        student.getStudentName(),
                        student.getAge(),
                        student.getDepartment(),
                        student.getMarks());
            }
        }

        System.out.println("------------------------------------------------------------------");

    }

    public static void searchStudent() {
        System.out.println("Enter Student ID: ");
        String idStr = scanner.nextLine().trim();

        try {
            int id = Integer.parseInt(idStr);
            int index = findStudentByIndex(id);
            if (index != -1) {
                Student s = students[index];
                System.out.println("\nStudent Found\n");
                System.out.println("ID          : " + s.getStudentID());
                System.out.println("Name        : " + s.getStudentName());
                System.out.println("Age         : " + s.getAge());
                System.out.println("Department  : " + s.getDepartment());
                System.out.println("Marks       : " + s.getMarks());
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID.");
        }

    }

    public static void updateStudent() {
        System.out.println("Enter Student ID to update: ");
        String idStr = scanner.nextLine().trim();
        try {
            int id = Integer.parseInt(idStr);
            int index = findStudentByIndex(id);
            if (index == -1) {
                System.out.println("Student not found.");
                return;
            }

            Student s = students[index];
            System.out.println("Enter New Name: ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                s.setStudentName(newName);
            }

            System.out.println("Enter New Age: ");
            String ageStr = scanner.nextLine().trim();
            if (!ageStr.isEmpty()) {
                s.setAge(Integer.parseInt(ageStr));
            }

            System.out.println("Enter New Department: ");
            String newDept = scanner.nextLine().trim();
            if (!newDept.isEmpty()) {
                s.setDepartment(newDept);
            }

            System.out.println("Enter New marks: ");
            String marksStr = scanner.nextLine().trim();
            if (!marksStr.isEmpty()) {
                double m = Double.parseDouble(marksStr);
                if (m>=0 && m<=100){
                    s.setMarks(m);
                } else {
                    System.out.println("Invalid Marks. Records Not Changed.");

                }
            }
            System.out.println("Student updated successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input format.");

        }
    }

    public static void deleteStudent() {
        System.out.println("Enter Student ID to delete: ");
        String idStr = scanner.nextLine().trim();
        try {
            int id = Integer.parseInt(idStr);
            int index = findStudentByIndex(id);
            if (index != -1) {
                for (int i = index; i < count - 1; i++) {
                    students[i] = students[i + 1];
                }
                students[count - 1] = null;
                count--;

                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID.");
        }

    }


    public static void displayStudentByDepartment() {
        if (count == 0) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("Enter Department: ");
        String deptInput = scanner.nextLine().trim();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].getDepartment().equalsIgnoreCase(deptInput)) {
                if (!found) {
                    System.out.println("\n------- " + deptInput.toUpperCase() + " STUDENTS --------------");
                    found = true;
                }
                System.out.printf("%-6d %-10s %-6s %.1f\n",
                        students[i].getStudentID(),
                        students[i].getStudentName(),
                        students[i].getDepartment(),
                        students[i].getMarks());
            }
        }

        if (found) {
            System.out.println("-----------------------------------");
        } else {
            System.out.println("No students found in " + deptInput + " department.");

        }
    }


    public static void displayTopStudent() {
        if (count == 0) {
            System.out.println("No records found.");
            return;
        }

        Student topStudent = students[0];
        for (int i = 0; i < count; i++) {
            if (students[i].getMarks() > topStudent.getMarks()) {
                topStudent = students[i];
            }
        }

        System.out.println("\n========================================");
        System.out.println("               TOP STUDENT                ");
        System.out.println("========================================");
        System.out.println("ID          : " + topStudent.getStudentID());
        System.out.println("Name        : " + topStudent.getStudentName());
        System.out.println("Department  : " + topStudent.getDepartment());
        System.out.println("Marks       : " + topStudent.getMarks());
        System.out.println("========================================");

    }


    public static void displayStudentMap() {
        if (count == 0) {
            System.out.println("No records found.");
            return;
        }

        Map<Integer, String> studentMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            studentMap.put(students[i].getStudentID(), students[i].getStudentName());
        }

        System.out.println("\n========================================");
        System.out.println("               STUDENT MAP              ");
        System.out.println("========================================");

        System.out.println("Insertion Order Maintained");
        for (Map.Entry<Integer, String> entry : studentMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void searchStudentUsingMap() {
        if (count == 0) {
            System.out.println("No records found.");
            return;
        }

        Map<Integer, Student> lookupMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            lookupMap.put(students[i].getStudentID(), students[i]);
        }

        System.out.println("Enter Student ID: ");
        String idStr = scanner.nextLine().trim();
        try {
            int targetID = Integer.parseInt(idStr);
            if (lookupMap.containsKey(targetID)) {
                Student s = lookupMap.get(targetID);
                System.out.println("\nStudent Found\n");
                System.out.println("ID          : " + s.getStudentID());
                System.out.println("Name        : " + s.getStudentName());
                System.out.println("Department  : " + s.getDepartment());
                System.out.println("Marks       : " + s.getMarks());
            } else {
                System.out.println("Student not found.");

            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID format");
        }
    }

    public static void displayStudentLinkedHashMap() {
        if (count == 0) {
            System.out.println("No records found.");
            return;
        }

        Map<Integer, String> linkedMap = new LinkedHashMap<>();
        for (int i = 0; i < count; i++) {
            linkedMap.put(students[i].getStudentID(), students[i].getStudentName());
        }

        System.out.println("Insertion Order Maintained");
        for (Map.Entry<Integer, String> entry : linkedMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    private static int findStudentByIndex(int ID) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentID() == ID)
                return i;
        }
        return -1;
    }

    private void printSingleStudent(Student s) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("Student Record");
        System.out.println("------------------------------------------------------------------");
        System.out.println("ID : " + s.getStudentID());
        System.out.println("Name : " + s.getStudentName());
        System.out.println("Age : " + s.getAge());
        System.out.println("Department : " + s.getDepartment());
        System.out.println("Marks : " + s.getMarks());
        System.out.println("------------------------------------------------------------------");


    }


}
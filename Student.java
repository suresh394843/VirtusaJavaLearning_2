package org.example;

public class Student {
    private int studentID;
    private String studentName;
    private int age;
    private String department;
    private double marks;

    public Student(int studentID, String studentName, int age, String department, double marks) {
        this.studentID = studentID;
        this.age = age;
        this.studentName = studentName;
        this.department = department;
        this.marks = marks;

    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void displayStudent(){
        System.out.printf("%-10d %-15s %-8d %-15s %-10.1f\n",studentID, studentName, age, department, marks);

    }


}
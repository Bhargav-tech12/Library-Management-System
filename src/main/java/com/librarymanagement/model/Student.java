package com.librarymanagement.model;

import java.util.Objects;

public class Student {
    private String studentId;
    private String name;
    private String department;
    private int year;
    private String phone;
    private String email;

    public Student() {
    }

    public Student(String studentId, String name, String department, int year, String phone, String email) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.year = year;
        this.phone = phone;
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Student)) {
            return false;
        }
        Student student = (Student) object;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{"
                + "studentId='" + studentId + '\''
                + ", name='" + name + '\''
                + ", department='" + department + '\''
                + ", year=" + year
                + ", phone='" + phone + '\''
                + ", email='" + email + '\''
                + '}';
    }
}

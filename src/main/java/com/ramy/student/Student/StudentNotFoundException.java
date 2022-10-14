package com.ramy.student.Student;

public class StudentNotFoundException extends RuntimeException {
    StudentNotFoundException(Long id) {
        super(String.format("Student with id %s is not found", id));
    }
}

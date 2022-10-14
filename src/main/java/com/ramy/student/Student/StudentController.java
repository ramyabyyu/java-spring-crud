package com.ramy.student.Student;

import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    List<Student> all() {
        return studentRepository.findAll();
    }

    @PostMapping("/student")
    Student save(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @GetMapping("/student/{id}")
    Student get(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @PutMapping("/student/{id}")
    Student update(@PathVariable Long id, @RequestBody Student newStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setEmail(newStudent.getEmail());
            student.setName(newStudent.getName());
            return student;
        }).orElseGet(() -> {
            newStudent.setId(id);
            return studentRepository.save(newStudent);
        });
    }

    @DeleteMapping("/student/{id}")
    String delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "Success deleting";
    }
}

package com.project.school.controller;

import com.project.school.entities.Student;
import com.project.school.service.StudentService;
import com.project.school.config.generatePassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error getting student: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            generatePassword passwordGenerator = new generatePassword();
            String generatedPassword = passwordGenerator.Password(generatePassword.UserType.STUDENT);
            student.setPassword(generatedPassword);
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error creating student: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable UUID id, @RequestBody Student student) {
        try {
            Student studentExists = studentService.getStudentById(id);
            if (studentExists == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Student updatedStudent = studentService.updateStudent(id, student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID id) {
        try {
            Student studentExists = studentService.getStudentById(id);
            if (studentExists == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            studentService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

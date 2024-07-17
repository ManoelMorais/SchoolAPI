package com.project.school.service;

import com.project.school.entities.Student;
import com.project.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(UUID id, Student student) {
        Student studentToUpdate = studentRepository.findById(id).orElse(null);
        if (studentToUpdate == null) {
            return null;
        }
        studentToUpdate.setName(student.getName());
        studentToUpdate.setAge(student.getAge());
        studentToUpdate.setResponse(student.getResponse());
        studentToUpdate.setNota(student.getNota());
        return studentRepository.save(studentToUpdate);
    }

}

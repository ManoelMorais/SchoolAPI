package com.project.school.config;

import com.project.school.DTO.StudentNotaDTO;
import com.project.school.entities.Nota;
import com.project.school.entities.Student;
import com.project.school.repository.NotaRepository;
import com.project.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentNotaService {

    private final StudentRepository studentRepository;
    private final NotaRepository notaRepository;

    public StudentNotaService(StudentRepository studentRepository, NotaRepository notaRepository) {
        this.studentRepository = studentRepository;
        this.notaRepository = notaRepository;
    }

    public Optional<StudentNotaDTO> getStudentNotaById(UUID studentId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            Optional<Nota> notaOpt = notaRepository.findById(studentId);
            Nota nota = notaOpt.orElse(new Nota());
            return Optional.of(new StudentNotaDTO(student.getName(), student.getResponse(), student.getAge(), nota));
        }
        return Optional.empty();
    }
}

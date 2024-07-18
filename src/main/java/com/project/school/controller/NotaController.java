package com.project.school.controller;

import com.project.school.entities.Nota;
import com.project.school.entities.Student;
import com.project.school.service.NotaService;
import com.project.school.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/students/notas")
public class NotaController {

    private final NotaService notaService;
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Nota>> getAllNotas() {
        return ResponseEntity.ok(notaService.getAllNotas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> getNotaById(@PathVariable UUID id) {
        return notaService.getNotaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<Nota> createNota(@PathVariable UUID studentId, @RequestBody Nota nota) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        nota.setStudent(student);
        Nota savedNota = notaService.saveNota(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable UUID id, @RequestBody Nota nota) {
        return notaService.getNotaById(id)
                .map(existingNota -> ResponseEntity.ok(notaService.updateNota(id, nota)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable UUID id) {
        Optional<Nota> notaOptional = notaService.getNotaById(id);
        if (notaOptional.isPresent()) {
            notaService.deleteNota(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
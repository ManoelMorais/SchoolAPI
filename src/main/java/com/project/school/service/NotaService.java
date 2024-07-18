package com.project.school.service;

import com.project.school.entities.Nota;
import com.project.school.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public List<Nota> getAllNotas() {
        return notaRepository.findAll();
    }

    public Optional<Nota> getNotaById(UUID id) {
        return notaRepository.findById(id);
    }

    public Nota saveNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public void deleteNota(UUID id) {
        notaRepository.deleteById(id);
    }

    public Nota updateNota(UUID id, Nota nota) {
        Nota notaToUpdate = notaRepository.findById(id).orElse(null);
        if (notaToUpdate == null) {
            return null;
        }
        notaToUpdate.setNota(nota.getNota());
        notaToUpdate.setClassStudent(nota.getClassStudent());
        notaToUpdate.setBehavior(nota.getBehavior());
        return notaRepository.save(notaToUpdate);
    }

}

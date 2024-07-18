package com.project.school.repository;

import com.project.school.entities.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotaRepository extends JpaRepository<Nota, UUID> {
}

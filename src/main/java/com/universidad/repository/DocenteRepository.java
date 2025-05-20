package com.universidad.repository;

import com.universidad.model.Docente;
import com.universidad.model.Estudiante;

import jakarta.persistence.LockModeType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
       // No es necesario implementar métodos básicos como findAll, ya que JpaRepository los proporciona automáticamente.


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Docente> findById(Long id); // Método para encontrar un estudiante por su ID con bloqueo pesimista
    // Este método se utiliza para evitar condiciones de carrera al actualizar el estudiante
    

}

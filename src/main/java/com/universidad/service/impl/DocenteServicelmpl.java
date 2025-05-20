package com.universidad.service.impl; // Define el paquete al que pertenece esta clase
import com.universidad.dto.DocenteDTO;
import com.universidad.model.Docente; // Importa la clase Estudiante del paquete model
import com.universidad.model.Estudiante;
import com.universidad.model.Materia;
import com.universidad.repository.DocenteRepository;
import com.universidad.repository.EstudianteRepository; // Importa la clase EstudianteRepository del paquete repository
import com.universidad.service.IDocenteService; // Importa la interfaz IEstudianteService del paquete service
import com.universidad.validation.EstudianteValidator; // Importa la clase EstudianteValidator del paquete validation

import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring
import org.springframework.stereotype.Service; // Importa la anotación Service de Spring
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List; // Importa la interfaz List para manejar listas
import java.util.stream.Collectors; // Importa la clase Collectors para manejar colecciones



@Service
public class DocenteServicelmpl implements IDocenteService{


    @Autowired
    private DocenteRepository docenteRepository;

    // Método utilitario para mapear Materia a DocenteDTO
    private DocenteDTO mapToDTO(Docente docente) {
        if (docente == null) return null;
        return DocenteDTO.builder()
                .id(docente.getId())
                .nombre(docente.getNombre())
                .apellido(docente.getApellido())
                .email(docente.getEmail())
                .fechaNacimiento(docente.getFechaNacimiento())
                .nroEmpleado(docente.getNroEmpleado())
                .departamento(docente.getDepartamento())
                .build();
    }

    @Override
    @Cacheable(value = "docentes")
    public List<DocenteDTO> obtenerTodosLosDocentes() {
        return docenteRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    @Override
    @CachePut(value = "docente", key = "#result.id")
    @CacheEvict(value = "docentes", allEntries = true)
    public DocenteDTO crearDocente(DocenteDTO docenteDTO) {
        System.out.println("segunda prueba" + docenteDTO);
        Docente docente = new Docente();
        docente.setNombre(docenteDTO.getNombre());
        docente.setApellido(docenteDTO.getApellido());
        docente.setEmail(docenteDTO.getEmail());
        docente.setFechaNacimiento(docenteDTO.getFechaNacimiento());
        docente.setNroEmpleado(docenteDTO.getNroEmpleado());
        docente.setDepartamento(docenteDTO.getDepartamento());
        Docente savedDocente = docenteRepository.save(docente);
        return mapToDTO(savedDocente);
    }

    @Override
    @CachePut(value = "materia", key = "#id")
    @CacheEvict(value = "materias", allEntries = true)
    public DocenteDTO actualizarDocente(Long id, DocenteDTO docenteDTO) {
        Docente docente = docenteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Materia not found"));
        docente.setNombre(docenteDTO.getNombre());
        docente.setApellido(docenteDTO.getApellido());
        docente.setEmail(docenteDTO.getEmail());
        docente.setNroEmpleado(docenteDTO.getNroEmpleado());
        docente.setDepartamento(docenteDTO.getDepartamento());

        // Map other fields as necessary
        Docente updateDocente = docenteRepository.save(docente);
        return mapToDTO(updateDocente);
    }

}

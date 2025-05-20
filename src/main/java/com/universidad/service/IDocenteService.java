package com.universidad.service; // Define el paquete al que pertenece esta interfaz

import com.universidad.dto.DocenteDTO; // Importa la clase EstudianteDTO del paquete dto
import com.universidad.model.Docente;
import com.universidad.model.Materia;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List; // Importa la interfaz List para manejar listas

public interface IDocenteService { // Define la interfaz IEstudianteService
    
    /**
     * Obtiene todos los estudiantes.
     * @return Lista de EstudianteDTO.
     */  
    List<DocenteDTO> obtenerTodosLosDocentes(); // Método para obtener una lista de todos los EstudianteDTO


    /**
     * Obtiene las materias de un estudiante por su ID.
     * @param estudianteId ID del estudiante.
     * @return Lista de materias del estudiante.
     */
  //  public List<Materia> obtenerMateriasDeEstudiante(Long estudianteId); // Método para obtener las materias de un estudiante por su ID

    
    /**
     * Crea un nuevo estudiante.
     * @param estudianteDTO DTO del estudiante a crear.
     * @return EstudianteDTO creado.
     */
    DocenteDTO crearDocente(DocenteDTO docenteDTO); // Método para crear un nuevo estudiante
    
    /**
     * Actualiza un estudiante existente.
     * @param id ID del estudiante a actualizar.
     * @param estudianteDTO DTO del estudiante con los nuevos datos.
     * @return EstudianteDTO actualizado.
     *
     * @throws RuntimeException si el estudiante no se encuentra.
     */
    DocenteDTO actualizarDocente(Long id, DocenteDTO docenteDTO); // Método para actualizar un estudiante existente

    /**
     * Elimina un estudiante por su ID.
     * @param id ID del estudiante a eliminar.
     */
//    DocenteDTO eliminarDocente(Long id, DocenteDTO docenteDTO); // Método para eliminar (de manera logica) un estudiante por su ID

}   

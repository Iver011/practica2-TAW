package com.universidad.controller;

import com.universidad.model.Docente;
import com.universidad.service.IDocenteService;

import jakarta.transaction.Transactional;

import com.universidad.dto.DocenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    private final IDocenteService docenteService;
    private static final Logger logger = LoggerFactory.getLogger(DocenteController.class);

    @Autowired
    public DocenteController(IDocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> obtenerTodosLosDocentes() {
        long inicio = System.currentTimeMillis();
        logger.info("[MATERIA] Inicio obtenerTodasLasMaterias: {}", inicio);
        List<DocenteDTO> result = docenteService.obtenerTodosLosDocentes();
        long fin = System.currentTimeMillis();
        logger.info("[MATERIA] Fin obtenerTodasLasMaterias: {} (Duracion: {} ms)", fin, (fin-inicio));
        return ResponseEntity.ok(result);
    }

 /*   @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<MateriaDTO> obtenerMateriaPorId(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[MATERIA] Inicio obtenerMateriaPorId: {}", inicio);
        MateriaDTO materia = materiaService.obtenerMateriaPorId(id);
        long fin = System.currentTimeMillis();
        logger.info("[MATERIA] Fin obtenerMateriaPorId: {} (Duracion: {} ms)", fin, (fin-inicio));
        if (materia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materia);
    } */

    @PostMapping
    public ResponseEntity<DocenteDTO> crearDocente(@RequestBody DocenteDTO docente) {
        //MateriaDTO materiaDTO = new MateriaDTO(materia.getId(), materia.getNombre(), materia.getCodigoUnico());
        System.out.println("prueba"+ docente);
        DocenteDTO nueva = docenteService.crearDocente(docente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DocenteDTO> actualizarDocente(@PathVariable Long id, @RequestBody DocenteDTO docente) {
        //MateriaDTO materiaDTO = new MateriaDTO(materia.getId(), materia.getNombreMateria(), materia.getCodigoUnico());
        DocenteDTO actualizadaDTO = docenteService.actualizarDocente(id, docente);
        //Materia actualizada = new Materia(actualizadaDTO.getId(), actualizadaDTO.getNombre(), actualizadaDTO.getCodigoUnico());
        return ResponseEntity.ok(actualizadaDTO);
    }

}

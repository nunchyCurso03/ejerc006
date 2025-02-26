package es.santander.ascender.ejerc006.controller;

import es.santander.ascender.ejerc006.model.Aula;
import es.santander.ascender.ejerc006.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    // Crear un Aula
    @PostMapping
    public ResponseEntity<Aula> createAula(@RequestBody Aula aula) {
        Aula createdAula = aulaService.create(aula);
        return ResponseEntity.ok(createdAula);
    }

    // Leer un Aula por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aula> getAula(@PathVariable Long id) {
        Aula aula = aulaService.read(id);
        if (aula != null) {
            return ResponseEntity.ok(aula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar todos los Aulas
    @GetMapping
    public ResponseEntity<List<Aula>> getAllAulas() {
        List<Aula> aulas = aulaService.list();
        return ResponseEntity.ok(aulas);
    }

    // Actualizar un Aula
    @PutMapping("/{id}")
    public ResponseEntity<Aula> updateAula(@PathVariable Long id, @RequestBody Aula aula) {
        aula.setId(id);
        Aula updatedAula = aulaService.update(aula);
        if (updatedAula != null) {
            return ResponseEntity.ok(updatedAula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un Aula por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAula(@PathVariable Long id) {
        aulaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


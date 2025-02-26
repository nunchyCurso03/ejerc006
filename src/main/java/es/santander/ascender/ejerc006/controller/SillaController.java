package es.santander.ascender.ejerc006.controller;

import es.santander.ascender.ejerc006.model.Silla;
import es.santander.ascender.ejerc006.service.SillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sillas")
public class SillaController {

    @Autowired
    private SillaService sillaService;

    // Crear una silla
    @PostMapping
    public ResponseEntity<Silla> createSilla(@RequestBody Silla silla) {
        Silla createdSilla = sillaService.create(silla);
        return ResponseEntity.ok(createdSilla);
    }

    // Leer una silla por ID
    @GetMapping("/{id}")
    public ResponseEntity<Silla> getSilla(@PathVariable Long id) {
        Silla silla = sillaService.read(id);
        if (silla == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(silla);
    }

    // Listar todas las sillas
    @GetMapping
    public ResponseEntity<List<Silla>> getAllSillas() {
        List<Silla> sillas = sillaService.list();
        return ResponseEntity.ok(sillas);
    }

    // Mover una silla a otra mesa
    @PutMapping("/{sillaId}/mover/{nuevaMesaId}")
    public ResponseEntity<Silla> moverSilla(@PathVariable Long sillaId, @PathVariable Long nuevaMesaId) {
        Silla sillaMovida = sillaService.moverSilla(sillaId, nuevaMesaId);
        return ResponseEntity.ok(sillaMovida);
    }

    // Eliminar una silla por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSilla(@PathVariable Long id) {
        sillaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package es.santander.ascender.ejerc006.controller;

import es.santander.ascender.ejerc006.model.Mesa;
import es.santander.ascender.ejerc006.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    // Crear una Mesa
    @PostMapping
    public ResponseEntity<Mesa> create(@RequestBody Mesa mesa) {
        Mesa nuevaMesa = mesaService.create(mesa);
        return ResponseEntity.status(201).body(nuevaMesa); // Código de estado 201 Created
    }

    // Obtener una Mesa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getById(@PathVariable Long id) {
        Mesa mesa = mesaService.read(id);
        if (mesa != null) {
            return ResponseEntity.ok(mesa); // Código de estado 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Código de estado 404 NOT FOUND
        }
    }

    // Listar todas las Mesas
    @GetMapping
    public ResponseEntity<List<Mesa>> getAll() {
        List<Mesa> mesas = mesaService.list();
        return ResponseEntity.ok(mesas); // Código de estado 200 OK
    }

    // Actualizar una Mesa
    @PutMapping("/{id}")
    public ResponseEntity<Mesa> update(@PathVariable Long id, @RequestBody Mesa mesa) {
        mesa.setId(id); // Asignamos el ID recibido en la URL a la mesa que estamos actualizando
        Mesa mesaActualizada = mesaService.update(mesa);
        return ResponseEntity.ok(mesaActualizada); // Código de estado 200 OK
    }

    // Eliminar una Mesa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mesaService.delete(id);
        return ResponseEntity.noContent().build(); // Código de estado 204 No Content
    }

    // Obtener mesas por ID de Aula
    @GetMapping("/aula/{aulaId}")
    public ResponseEntity<List<Mesa>> getByAulaId(@PathVariable Long aulaId) {
        List<Mesa> mesas = mesaService.findByAulaId(aulaId);
        return mesas.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(mesas);
    }

    // Mover una mesa a otro aula
    @PutMapping("/{mesaId}/mover/{nuevaAulaId}")
    public ResponseEntity<Mesa> moveToNewAula(@PathVariable Long mesaId, @PathVariable Long nuevaAulaId) {
        Mesa mesaActualizada = mesaService.moveToNewAula(mesaId, nuevaAulaId);
        return ResponseEntity.ok(mesaActualizada);
    }

}

package es.santander.ascender.ejerc006.controller;

import es.santander.ascender.ejerc006.model.Edificio;
import es.santander.ascender.ejerc006.service.EdificioService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edificio")
public class EdificioController {

    @Autowired
    private EdificioService edificioService;

    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public Edificio create(@RequestBody Edificio edificio) {
        return edificioService.create(edificio);
    }

    @GetMapping("/{id}")
    public Edificio read(@PathVariable Long id) {
        return edificioService.read(id);
    }

    @GetMapping
    public List<Edificio> list() {
        return edificioService.list();
    }

    @PutMapping
    public Edificio update(@RequestBody Edificio edificio) {
        return edificioService.update(edificio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        edificioService.delete(id);
    }
}

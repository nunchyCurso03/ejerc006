package es.santander.ascender.ejerc006.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.santander.ascender.ejerc006.model.Silla;
import es.santander.ascender.ejerc006.repository.SillaRepository;

@Service
public class SillaService {

    @Autowired
    private SillaRepository sillaRepository;

    // Crear una Silla
    public Silla create(Silla silla) {
        if (silla.getId() != null) {
            throw new CrudSecurityException("No se puede crear una silla con ID ya existente",
                    CRUDOperation.CREATE,
                    silla.getId());
        }
        return sillaRepository.save(silla);
    }

    // Leer una Silla por ID
    @Transactional(readOnly = true)
    public Silla read(Long id) {
        return sillaRepository.findById(id).orElse(null);
    }

    // Listar todas las Sillas
    @Transactional(readOnly = true)
    public List<Silla> list() {
        return sillaRepository.findAll();
    }

    // Listar todas las Sillas de una Mesa específica
    @Transactional(readOnly = true)
    public List<Silla> findByMesaId(Long mesaId) {
        return sillaRepository.findByMesaId(mesaId);
    }

    // Actualizar una Silla
    public Silla update(Silla silla) {
        if (silla.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear una silla utilizando modificar",
                    CRUDOperation.UPDATE,
                    null);
        }
        return sillaRepository.save(silla);
    }

    // Eliminar una Silla por ID
    public void delete(Long id) {
        sillaRepository.deleteById(id);
    }

    // Mover una silla a otra mesa
    public Silla moverSilla(Long sillaId, Long nuevaMesaId) {
        Silla silla = sillaRepository.findById(sillaId)
                .orElseThrow(() -> new RuntimeException("Silla no encontrada"));
        silla.setMesaId(nuevaMesaId);
        return sillaRepository.save(silla);
    }
}

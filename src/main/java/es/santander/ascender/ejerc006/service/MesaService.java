package es.santander.ascender.ejerc006.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc006.model.Mesa;
import es.santander.ascender.ejerc006.model.Silla;
import es.santander.ascender.ejerc006.repository.MesaRepository;
import es.santander.ascender.ejerc006.repository.SillaRepository;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

     @Autowired
    private SillaRepository sillaRepository;

    // Crear una Mesa
    public Mesa create(Mesa mesa) {
        if (mesa.getId() != null) {
            throw new CrudSecurityException("No se puede crear una mesa con ID ya existente",
                    CRUDOperation.CREATE, mesa.getId());
        }
        return mesaRepository.save(mesa);
    }

    // Leer una Mesa por ID
    @Transactional(readOnly = true)
    public Mesa read(Long id) {
        return mesaRepository.findById(id).orElse(null);
    }

    // Listar todas las Mesas
    @Transactional(readOnly = true)
    public List<Mesa> list() {
        return mesaRepository.findAll();
    }

    // Listar todas las Mesas de un Aula específica
    @Transactional(readOnly = true)
    public List<Mesa> findByAulaId(Long aulaId) {
        return mesaRepository.findByAulaId(aulaId);
    }

    // Actualizar una Mesa
    public Mesa update(Mesa mesa) {
        if (mesa.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro mesa utilizando modificar",
                    CRUDOperation.UPDATE, null);
        }
        return mesaRepository.save(mesa);
    }

    // Eliminar una Mesa por ID
    public void delete(Long id) {
        mesaRepository.deleteById(id);
    }

    // Método para mover una mesa a otro aula
    public Mesa moveToNewAula(Long mesaId, Long nuevaAulaId) {
        Mesa mesa = mesaRepository.findById(mesaId).orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        mesa.setAula_id(nuevaAulaId);

        // Actualizamos las sillas que pertenecen a esta mesa
        List<Silla> sillas = sillaRepository.findByMesaId(mesaId);
        for (Silla silla : sillas) {
            silla.setMesaId(mesaId);
            sillaRepository.save(silla);  // Guardamos la silla con el nuevo mesa_id
        }

        return mesaRepository.save(mesa);  // Guardamos la mesa con el nuevo aula_id
    }
}

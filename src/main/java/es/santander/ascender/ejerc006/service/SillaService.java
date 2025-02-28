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
public class SillaService {

    @Autowired
    private SillaRepository sillaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    // Crear una Silla
    public Silla create(Silla silla) {
        if (silla.getId() != null) {
            throw new CrudSecurityException("No se puede crear una silla con ID ya existente",
                    CRUDOperation.CREATE,
                    silla.getId());
        }

        // Buscar la mesa a la que se quiere agregar la silla
        Mesa mesa = mesaRepository.findById(silla.getMesaId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        // Verificar que la mesa no supere la capacidad máxima de sillas
        if (mesa.getNumeroSillas() >= mesa.getCapacidadMaximaSillas()) {
            throw new RuntimeException("No se pueden agregar más sillas. Capacidad máxima alcanzada.");
        }

        // Incrementar el número de sillas en la mesa
        mesa.setNumeroSillas(mesa.getNumeroSillas() + 1);
        mesaRepository.save(mesa); // Guardamos el cambio en la base de datos

        // Guardar la nueva silla
        silla = sillaRepository.save(silla);
        silla.generarNombre(silla.getMesaId()); // Genera el nombre
        silla = sillaRepository.save(silla);
        return silla;
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

    // Mover una silla a otra mesa
    @Transactional
    public Silla moverSilla(Long sillaId, Long nuevaMesaId) {
        Silla silla = sillaRepository.findById(sillaId)
                .orElseThrow(() -> new RuntimeException("Silla no encontrada"));

        // Buscar la mesa actual y la nueva mesa
        Mesa mesaActual = mesaRepository.findById(silla.getMesaId())
                .orElseThrow(() -> new RuntimeException("Mesa actual no encontrada"));

        Mesa nuevaMesa = mesaRepository.findById(nuevaMesaId)
                .orElseThrow(() -> new RuntimeException("Mesa destino no encontrada"));

        // Verificar que la nueva mesa tenga espacio
        if (nuevaMesa.getNumeroSillas() >= nuevaMesa.getCapacidadMaximaSillas()) {
            throw new RuntimeException("No se puede mover la silla. Capacidad máxima alcanzada en la mesa destino.");
        }

         // Actualizar el número de sillas en ambas mesas
         mesaActual.setNumeroSillas(mesaActual.getNumeroSillas() - 1);
         nuevaMesa.setNumeroSillas(nuevaMesa.getNumeroSillas() + 1);
 
         mesaRepository.save(mesaActual);
         mesaRepository.save(nuevaMesa);

        silla.setMesaId(nuevaMesaId);
        return sillaRepository.save(silla);
    }

    // Eliminar una Silla por ID
    public void delete(Long id) {
        sillaRepository.deleteById(id);
    }
}

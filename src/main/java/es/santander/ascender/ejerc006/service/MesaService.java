package es.santander.ascender.ejerc006.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc006.model.Aula;
import es.santander.ascender.ejerc006.model.Mesa;
import es.santander.ascender.ejerc006.model.Silla;
import es.santander.ascender.ejerc006.repository.AulaRepository;
import es.santander.ascender.ejerc006.repository.MesaRepository;
import es.santander.ascender.ejerc006.repository.SillaRepository;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private SillaRepository sillaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    // Crear una Mesa
    public Mesa create(Mesa mesa) {

        if (mesa.getId() != null) {
            throw new CrudSecurityException("No se puede crear una mesa con ID ya existente",
                    CRUDOperation.CREATE, mesa.getId());
        }
        // Obtener el aula a la que pertenece la mesa
        Aula aula = aulaRepository.findById(mesa.getAulaId())
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        // Contar cuántas mesas hay en el aula
        int numMesasEnAula = mesaRepository.countByAulaId(mesa.getAulaId());

        // Verificar que no se supere la capacidad máxima
        if (numMesasEnAula >= aula.getCapacidadMaximaMesas()) {
            throw new RuntimeException("No se pueden agregar más mesas. Capacidad máxima alcanzada.");
        }

        // Incrementar el número de mesas en el aula
        aula.incrementarNumeroMesas();
        aulaRepository.save(aula); // Guarda el cambio en la BD

        mesa = mesaRepository.save(mesa);
        mesa.generarNombre(mesa.getAulaId()); // Genera el nombre

        // Guarda de nuevo la mesa para persistir el nombre generado
        mesa = mesaRepository.save(mesa);

        return mesa;
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

    /* Actualizar una Mesa */
    public Mesa update(Mesa mesa) {
        if (mesa.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro mesa utilizando modificar",
                    CRUDOperation.UPDATE, null);
        }
        return mesaRepository.save(mesa);
    }

    /* Eliminar una Mesa por ID */

    public void delete(Long id) {

        // Buscar la mesa a eliminar
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new CrudSecurityException("Mesa no encontrada", CRUDOperation.DELETE, id));

        // Verifico si hay sillas asociadas a la mesa, si las hay , no se puede eliminar
        List<Silla> sillas = sillaRepository.findByMesaId(id);
        if (!sillas.isEmpty()) {
            throw new CrudSecurityException("No se puede eliminar la mesa porque tiene sillas asociadas",
                    CRUDOperation.DELETE, id);
        }

        // Busco el aula en el que está la mesa
        Aula aula = aulaRepository.findById(mesa.getAulaId())
                .orElseThrow(
                        () -> new CrudSecurityException("Aula no encontrada", CRUDOperation.READ, mesa.getAulaId()));

        // Restar 1 al número de mesas
        aula.setNumeroMesas(aula.getNumeroMesas() - 1);

        // Guardar los cambios en el aula
        aulaRepository.save(aula);
        mesaRepository.deleteById(id);
    }

    /* Método para mover una mesa a otro aula */
    public Mesa moveToNewAula(Long mesaId, Long nuevaAulaId) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new CrudSecurityException("Mesa no encontrada", CRUDOperation.READ, mesaId));

        // mesa.setAulaId(nuevaAulaId);

        // Comprobamos que existe el aulaOriginal
        Aula aulaOriginal = aulaRepository.findById(mesa.getAulaId())
                .orElseThrow(() -> new CrudSecurityException("Aula original no encontrada", CRUDOperation.READ,
                        mesa.getAulaId()));

        // verifica que el aula destino tenga espacio disponible antes de mover la mesa
        Aula nuevaAula = aulaRepository.findById(nuevaAulaId)
                .orElseThrow(
                        () -> new CrudSecurityException("Aula destino no encontrada", CRUDOperation.READ, nuevaAulaId));

        // Contar cuántas mesas hay en el aula destino
        int numMesasEnNuevaAula = mesaRepository.countByAulaId(nuevaAulaId);

        // Verificar que no se supere la capacidad máxima en el aula destino
        if (numMesasEnNuevaAula >= nuevaAula.getCapacidadMaximaMesas()) {
            throw new RuntimeException("No se puede mover la mesa. Capacidad máxima del aula alcanzada.");
        }

        // actualizo el numero de mesas
        aulaOriginal.setNumeroMesas(aulaOriginal.getNumeroMesas() - 1);
        nuevaAula.setNumeroMesas(nuevaAula.getNumeroMesas() + 1);

        // Guardamos los cambios en las aulas
        aulaRepository.save(aulaOriginal);
        aulaRepository.save(nuevaAula);

        // Mover la mesa al nuevo aula
        mesa.setAulaId(nuevaAulaId);
        mesaRepository.save(mesa);

        // Actualizamos las sillas que pertenecen a esta mesa
        List<Silla> sillas = sillaRepository.findByMesaId(mesaId);
        for (Silla silla : sillas) {
            silla.setMesaId(mesaId);
            sillaRepository.save(silla); // Guardamos la silla con el nuevo mesaId
        }

        return mesa; // Guardamos la mesa con el nuevo aulaId
    }
}

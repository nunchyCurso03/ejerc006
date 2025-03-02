package es.santander.ascender.ejerc006.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc006.model.Aula;
import es.santander.ascender.ejerc006.model.Mesa;
import es.santander.ascender.ejerc006.repository.AulaRepository;
import es.santander.ascender.ejerc006.repository.MesaRepository;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;
     @Autowired
    private MesaRepository mesaRepository;

    // Crear un Aula
    public Aula create(Aula aula) {
        if (aula.getId() != null) {
            throw new CrudSecurityException("No se puede crear un aula con ID ya existente",
                    CRUDOperation.CREATE, aula.getId());
        }
        return aulaRepository.save(aula);
    }

    // Leer un Aula por ID
    @Transactional(readOnly = true)
    public Aula read(Long id) {
        return aulaRepository.findById(id).orElse(null);
    }

    // Listar todas las Aulas
    @Transactional(readOnly = true)
    public List<Aula> list() {
        return aulaRepository.findAll();
    }

    // Actualizar un Aula
    public Aula update(Aula aula) {
        if (aula.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro aula utilizando modificar",
                    CRUDOperation.UPDATE, null);
        }
        return aulaRepository.save(aula);
    }

    // Eliminar un Aula por ID
    public void delete(Long id) {
        // Verifico si hay mesas asociadas al aula, si hay no se elimina el aula
        List<Mesa> mesas = mesaRepository.findByAulaId(id);
        if (!mesas.isEmpty()) {
            throw new CrudSecurityException("No se puede eliminar el aula porque tiene mesas asociadas", CRUDOperation.DELETE, id);
        }
        aulaRepository.deleteById(id);
    }
}

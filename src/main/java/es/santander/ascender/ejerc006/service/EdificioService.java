package es.santander.ascender.ejerc006.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc006.model.Edificio;
import es.santander.ascender.ejerc006.repository.EdificioRepository;

@Service
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;

    // Crear un Edificio
    public Edificio create(Edificio edificio) {

        if (edificio.getId() != null) {
            throw new CrudSecurityException("No se puede crear un edificio con ID ya existente",
                    CRUDOperation.CREATE,
                    edificio.getId());
        } else {
            return edificioRepository.save(edificio);
        }

    }

    // Leer un Edificio por ID
    @Transactional(readOnly = true)
    public Edificio read(Long id) {
        return edificioRepository.findById(id).orElse(null);
    }

    // Listar todos los edificio
    @Transactional(readOnly = true)
    public List<Edificio> list() {
        return edificioRepository.findAll();
    }

    // Actualizar un edificio
    public Edificio update(Edificio edificio) {
        if (edificio.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro edificio utilizando modificar",
                    CRUDOperation.UPDATE,
                    null);
        }
        return edificioRepository.save(edificio);
    }

    // Eliminar un documento por ID
    public void delete(Long id) {
        edificioRepository.deleteById(id);
    }

}

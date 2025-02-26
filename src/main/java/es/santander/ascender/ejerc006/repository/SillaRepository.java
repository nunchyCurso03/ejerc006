package es.santander.ascender.ejerc006.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santander.ascender.ejerc006.model.Silla;

public interface SillaRepository extends JpaRepository<Silla, Long>{

    // Buscar todas las sillas de una mesa específica
    List<Silla> findByMesaId(Long mesaId);

}

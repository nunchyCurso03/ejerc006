package es.santander.ascender.ejerc006.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santander.ascender.ejerc006.model.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Long>{
    
     // Buscar todas las mesas de un aula específica
     List<Mesa> findByAulaId(Long aulaId);

     //contar las mesas de un aula espececífica
     int countByAulaId(Long aulaId);

}

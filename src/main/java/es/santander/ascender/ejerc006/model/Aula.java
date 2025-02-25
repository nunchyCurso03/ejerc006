package es.santander.ascender.ejerc006.model;

import es.santander.ascender.ejerc006.model.enums.TipoAula;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "AULA")
public class Aula {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @Positive
    private Integer capacidadMaxima;

    @Column(nullable = false)
    @Positive
    private Integer numeroMesas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAula tipoAula; 

    @Column(nullable = false)
    private boolean proyector;

    @Column(nullable = false)
    private Long edificio_id;

    public Aula() {
    }

    public Aula(Long id, String nombre, TipoAula tipoAula, Integer capacidadMaxima, Integer numeroMesas, boolean proyector, Long edificio_id) {
        this.id = id;
        this.nombre = nombre;
        this.tipoAula = tipoAula;  
        this.capacidadMaxima = capacidadMaxima;
        this.numeroMesas = numeroMesas;           
        this.proyector = proyector;
        this.edificio_id = edificio_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }


    public Integer getNumeroMesas() {
        return numeroMesas;
    }

    public void setNumeroMesas(Integer numeroMesas) {
        this.numeroMesas = numeroMesas;
    }
    
    public TipoAula getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(TipoAula tipoAula) {
        this.tipoAula = tipoAula;
    }

    public boolean isProyector() {
        return proyector;
    }

    public void setProyector(boolean proyector) {
        this.proyector = proyector;
    }
 
    public Long getEdificio_id() {
        return edificio_id;
    }

    public void setEdificio_id(Long edificio_id) {
        this.edificio_id = edificio_id;
    }

 

}

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
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "AULA")
public class Aula {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer capacidadMaximaMesas;  

    @Column(nullable = false)
    @PositiveOrZero
    private Integer numeroMesas = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAula tipoAula; 

    @Column(nullable = false)
    private boolean proyector;

    @Column(nullable = false)
    private Long edificioId;

    public Aula() {
    }

    public Aula(Long id, String nombre, TipoAula tipoAula, Integer capacidadMaximaMesas, Integer numeroMesas, boolean proyector, Long edificioId) {
        this.id = id;
        this.nombre = nombre;
        this.tipoAula = tipoAula;  
        this.capacidadMaximaMesas = capacidadMaximaMesas;
        this.setNumeroMesas(numeroMesas); // Usamos el setter para validar           
        this.proyector = proyector;
        this.edificioId = edificioId;
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

    public Integer getCapacidadMaximaMesas() {
        return capacidadMaximaMesas;
    }

    public void setCapacidadMaximaMesas(Integer capacidadMaximaMesas) {
        
        if (capacidadMaximaMesas < this.numeroMesas) {
            throw new IllegalArgumentException("La capacidad máxima no puede ser menor al número actual de mesas.");
        }
        this.capacidadMaximaMesas = capacidadMaximaMesas;
    }


    public Integer getNumeroMesas() {
        return numeroMesas;
    }

    public void setNumeroMesas(Integer numeroMesas) {
     
        this.numeroMesas = numeroMesas;
    }

    public void incrementarNumeroMesas() {
        if (this.numeroMesas < this.capacidadMaximaMesas) {
            this.numeroMesas++;
        } else {
            throw new IllegalArgumentException("No se pueden agregar más mesas a esta aula.");
        }
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
 
    public Long getEdificioId() {
        return edificioId;
    }

    public void setEdificioId(Long edificioId) {
        this.edificioId = edificioId;
    }

 

}

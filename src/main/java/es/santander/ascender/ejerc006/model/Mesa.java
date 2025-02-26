package es.santander.ascender.ejerc006.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "MESA")
public class Mesa {

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
    private Integer numeroSillas;

    @Column(nullable = false)
    private Long aulaId;

    public Mesa() {
    }

    public Mesa(Long id,String nombre,  @Positive Integer capacidadMaxima, @Positive Integer numeroSillas, Long aulaId) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.numeroSillas = numeroSillas;
        this.aulaId = aulaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroSillas() {
        return numeroSillas;
    }

    public void setNumeroSillas(Integer numeroSillas) {
        this.numeroSillas = numeroSillas;
    }

    public Long getAulaId() {
        return aulaId;
    }

    public void setAula_id(Long aulaId) {
        this.aulaId = aulaId;
    }  

    // Método para generar el nombre de la mesa basado en el aula
    public void generarNombre(Long aulaId) {
        // Generar el nombre basado en el aulaId y el id de la mesa  
        this.nombre = "Mesa " + aulaId + "-" + this.id;
    }
    

}

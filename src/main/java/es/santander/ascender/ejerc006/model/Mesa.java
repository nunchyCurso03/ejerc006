package es.santander.ascender.ejerc006.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "MESA")
public class Mesa {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    @Positive
    private Integer capacidadMaximaSillas;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer numeroSillas = 0;

    @Column(nullable = false)
    private Long aulaId;

    public Mesa() {
    }

    public Mesa(Long id,String nombre,  @Positive Integer capacidadMaximaSillas, @Positive Integer numeroSillas, Long aulaId) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaximaSillas = capacidadMaximaSillas;
        this.setNumeroSillas(numeroSillas);  // Usamos el setter para validar
        this.aulaId = aulaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getCapacidadMaximaSillas() {
        return capacidadMaximaSillas;
    }

    public void setCapacidadMaximaSillas(Integer capacidadMaximaSillas) {
        if (capacidadMaximaSillas < this.numeroSillas) {
            throw new IllegalArgumentException("La capacidad máxima no puede ser menor al número actual de sillas.");
        }
        this.capacidadMaximaSillas = capacidadMaximaSillas;
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
        if (numeroSillas > this.capacidadMaximaSillas) {
            throw new IllegalArgumentException("El número de sillas no puede superar la capacidad máxima de la mesa.");
        }
        this.numeroSillas = numeroSillas;
    }
    public void incrementarNumeroSillas() {
        if (this.numeroSillas < this.capacidadMaximaSillas) {
            this.numeroSillas++;
        } else {
            throw new IllegalArgumentException("No se pueden agregar más sillas a esta Mesa.");
        }
    }

    public Long getAulaId() {
        return aulaId;
    }

    public void setAulaId(Long aulaId) {
        this.aulaId = aulaId;
    }  

    // Método para generar el nombre de la mesa basado en el aula
    public void generarNombre(Long aulaId) {
        // Generar el nombre basado en el aulaId y el id de la mesa  
        this.nombre = "Mesa " + aulaId + "-" + this.id;
    }
    

}

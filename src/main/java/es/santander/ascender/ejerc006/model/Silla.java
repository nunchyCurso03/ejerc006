package es.santander.ascender.ejerc006.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "SILLA")
public class Silla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
   @PositiveOrZero
    private Integer numeroSilla = 0;

    @Column(nullable = false)
    private boolean disponible;

    @Column(nullable = false)
    private Long mesaId;

    public Silla() {
    }

    public Silla(Long id, String nombre, boolean disponible, Long mesaId) {
        this.id = id;
        this.nombre = nombre;
        //this.numeroSilla = numeroSilla;
        this.disponible = disponible;
        this.mesaId = mesaId;
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

    /*public Integer getNumeroSilla() {
        return numeroSilla;
    }

    public void setNumeroSilla(Integer numeroSilla) {
        this.numeroSilla = numeroSilla;
    }*/

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Long getMesaId() {
        return mesaId;
    }

    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId;
    }

    // Método para generar el nombre de la silla basado en la mesa
    public void generarNombre(Long mesaId) {
         
        this.nombre = "Silla " + mesaId + "-" + this.id;
    }
}

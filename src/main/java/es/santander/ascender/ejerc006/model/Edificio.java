package es.santander.ascender.ejerc006.model;

import es.santander.ascender.ejerc006.model.enums.TipoEdificio; 

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
@Table(name = "EDIFICIO")
public class Edificio {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    @Positive
    private Integer numPlantas = 1;

    @Column(nullable = false)
    private Integer capacidadMaxima;

    @Enumerated(EnumType.STRING) 
    @Column(nullable = false)
    private TipoEdificio tipoEdificio;

    public Edificio() {
    }

    public Edificio(Long id, String nombre, String direccion, Integer numPlantas, Integer capacidadMaxima, TipoEdificio tipoEdificio) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numPlantas = numPlantas;
        this.capacidadMaxima = capacidadMaxima;
        this.tipoEdificio = tipoEdificio;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumPlantas() {
        return numPlantas;
    }

    public void setNumPlantas(Integer numPlantas) {
        this.numPlantas = numPlantas;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public TipoEdificio getTipoEdificio() {
        return tipoEdificio;
    }

    public void setTipoEdificio(TipoEdificio tipoEdificio) {
        this.tipoEdificio = tipoEdificio;
    }

     
    

}

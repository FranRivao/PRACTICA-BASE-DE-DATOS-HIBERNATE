package bbdd.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


// @TODO Realiza todas las anotaciones necesarias en esta clase para que
// que sus instancias sean guardadas en la base de datos utilizando
// Hibernate. Respecta las restricciones de modelado impuestas en el
// enunciado de la práctica. No es necesario modificar el código de esta
// clase, únicamente debes hacer las anotaciones que consideres
// necesarias.
@Entity
@Table(name = "pasajero")
public class Pasajero {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @OneToMany(mappedBy = "pasajero", cascade = CascadeType.ALL)
    private Set<Gasto> gastos = new HashSet<>();

    public Pasajero() {
        // requerido por Hibernate
    }

    public Pasajero(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Gasto> getGastos() { 
        return gastos; 
    }

}

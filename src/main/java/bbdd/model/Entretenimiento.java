package bbdd.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// @TODO Realiza todas las anotaciones necesarias en esta clase para que
// que sus instancias sean guardadas en la base de datos utilizando
// Hibernate. Respecta las restricciones de modelado impuestas en el
// enunciado de la práctica. No es necesario modificar el código de esta
// clase, únicamente debes hacer las anotaciones que consideres
// necesarias.
@Entity
@Table(name = "entretenimiento")
public class Entretenimiento {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @OneToMany(mappedBy = "entretenimiento", cascade = CascadeType.ALL)
    private final Set<Gasto> gastos = new HashSet<>();

    public Entretenimiento() {
        // requerido por Hibernate
    }

    public Entretenimiento(String nombre) {
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

package utp.edu.pe.Integrador_Backend.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SOP_CURSO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_CURSOID")
    private Long cursoId;

    @Column(name = "X_NOMBRECURSO")
    private String nombreCurso;

    @Enumerated(EnumType.STRING)
    @Column(name = "X_NIVEL")
    private Nivel nivel;

    @Column(name = "X_DESCRIPCION",length = 150)
    private String descripcion;

    // Relaciones
    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("curso")
    private Set<Subcurso> subcursos;
}

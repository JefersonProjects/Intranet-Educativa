package utp.edu.pe.Integrador_Backend.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SOP_NOTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_NOTAID")  // Clave primaria
    private Long notaId;

    @Column(name = "N_UNIDAD")
    private Integer unidad;

    @Column(name = "N_CALIFICACION")
    private Double calificacion;

    // Relaciones
    @ManyToOne // un alumno puede tener muchas notas en diferentes subcursos.
    @JoinColumn(name = "FK_SOP_ALUMNO_C_USUARIOID")  // FK a SOP_USUARIO_ALUMNO
    @JsonIgnoreProperties({"notas", "asignaciones"})
    private Alumno alumno;

    @ManyToOne //subcurso puede tener muchas notas de diferentes alumnos.
    @JoinColumn(name = "FK_SOP_SUBCURSO_C_SUBCURSOID")  // FK a SOP_CURSO_SUBCURSO
    @JsonIgnoreProperties({"notas", "asignacionesAlumno", "curso"})
    private Subcurso subcurso;
}
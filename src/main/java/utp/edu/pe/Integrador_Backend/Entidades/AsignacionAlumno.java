package utp.edu.pe.Integrador_Backend.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SOP_ASIGNACION_ALUMNO_DETALLE")
public class AsignacionAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ASIGNACIONALUMNOID")  // Clave primaria
    private Long asignacionAlumnoId;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "FK_SOP_ALUMNO_C_USUARIOID") // FK a SOP_USUARIO_ALUMNO
    @JsonIgnoreProperties({"asignaciones", "notas"})
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "FK_SOP_SUBCURSO_C_SUBCURSOID")
    @JsonIgnoreProperties({"asignacionesAlumno", "notas", "curso"})
    private Subcurso subcurso;

}



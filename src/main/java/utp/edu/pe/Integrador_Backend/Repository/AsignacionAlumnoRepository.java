package utp.edu.pe.Integrador_Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Integrador_Backend.Entidades.Alumno;
import utp.edu.pe.Integrador_Backend.Entidades.AsignacionAlumno;
import utp.edu.pe.Integrador_Backend.Entidades.Subcurso;

import java.util.List;

@Repository
public interface AsignacionAlumnoRepository extends JpaRepository<AsignacionAlumno, Long> {

    // Encontrar asignaciones por alumno
    List<AsignacionAlumno> findByAlumno(Alumno alumno);

    // Eliminar las asignaciones de los alumnos para un subcurso específico
    void deleteBySubcurso(Subcurso subcurso);

    // Encontrar asignaciones por subcurso
    List<AsignacionAlumno> findBySubcurso(Subcurso subcurso);
    boolean existsByAlumnoAndSubcurso(Alumno alumno, Subcurso subcurso);

}


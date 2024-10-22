package utp.edu.pe.Integrador_Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Integrador_Backend.Entidades.Alumno;
import utp.edu.pe.Integrador_Backend.Entidades.Nivel;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    //buscar alumnos por nivel
    List<Alumno> findByNivel(Nivel nivel);

    List<Alumno> findAll();

    boolean existsByCodigo(String codigo);

    long countByGradoAndNivel(Integer grado, Nivel nivel);

    // filtrar alumnos por grado y subcurso
    List<Alumno> findByGradoAndAsignaciones_Subcurso_SubcursoId(Integer grado, Long subcursoId);
}

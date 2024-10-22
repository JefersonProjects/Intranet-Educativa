package utp.edu.pe.Integrador_Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utp.edu.pe.Integrador_Backend.Entidades.AsignacionProfesor;
import utp.edu.pe.Integrador_Backend.Entidades.Subcurso;

import java.util.Optional;

public interface AsignacionProfesorRepository extends JpaRepository<AsignacionProfesor, Long> {

    // Verificar si un subcurso ya está asignado a otro profesor
    boolean existsBySubcurso_SubcursoId(Long subcursoId);

    void deleteBySubcurso(Subcurso subcurso);

    // Obtener el profesor que tiene asignado un subcurso
    Optional<AsignacionProfesor> findBySubcurso_SubcursoId(Long subcursoId);

}

package utp.edu.pe.Integrador_Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Integrador_Backend.Entidades.Alumno;
import utp.edu.pe.Integrador_Backend.Entidades.Nota;
import utp.edu.pe.Integrador_Backend.Entidades.Subcurso;

import java.util.List;

@Repository
public interface NotaRepository  extends JpaRepository<Nota, Long> {

    // Encontrar notas por alumno y subcurso
    List<Nota> findByAlumnoAndSubcurso(Alumno alumno, Subcurso subcurso);

    // Encontrar notas por subcurso
    List<Nota> findBySubcurso(Subcurso subcurso);
}

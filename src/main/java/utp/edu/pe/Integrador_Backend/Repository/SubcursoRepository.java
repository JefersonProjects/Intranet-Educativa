package utp.edu.pe.Integrador_Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Integrador_Backend.Entidades.Nivel;
import utp.edu.pe.Integrador_Backend.Entidades.Subcurso;

import java.util.List;

@Repository
public interface SubcursoRepository extends JpaRepository<Subcurso, Long> {
    List<Subcurso> findByNivel(Nivel nivel);
    boolean existsByNombreSubcursoIgnoreCaseAndNivel(String nombreSubcurso, Nivel nivel);
}

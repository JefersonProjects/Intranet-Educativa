package utp.edu.pe.Integrador_Backend.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Integrador_Backend.Entidades.Curso;
@Repository
public interface CursoRepository  extends JpaRepository<Curso, Long> {

}

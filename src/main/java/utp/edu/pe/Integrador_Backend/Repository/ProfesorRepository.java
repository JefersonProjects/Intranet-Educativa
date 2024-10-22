package utp.edu.pe.Integrador_Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utp.edu.pe.Integrador_Backend.Entidades.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    boolean existsByCodigo(String codigo);


    // metodo para buscar profesores cuyo DNI comience con un patron
    List<Profesor> findByDniStartingWith(String prefix);
}

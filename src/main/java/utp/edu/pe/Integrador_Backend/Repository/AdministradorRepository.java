package utp.edu.pe.Integrador_Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.Integrador_Backend.Entidades.Administrador;

import java.util.Optional;
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByDni(String dni);


    Optional<Administrador> findByCodigo(String correo);
}

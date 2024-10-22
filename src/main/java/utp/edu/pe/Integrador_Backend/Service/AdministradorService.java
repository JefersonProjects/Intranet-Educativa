package utp.edu.pe.Integrador_Backend.Service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Integrador_Backend.Entidades.ActualizarAdministradorRequest;
import utp.edu.pe.Integrador_Backend.Entidades.Administrador;
import utp.edu.pe.Integrador_Backend.Entidades.Rol;
import utp.edu.pe.Integrador_Backend.Repository.AdministradorRepository;

import java.util.List;
@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    private final Argon2 argon2 = Argon2Factory.create();

    // Metodo para listar administradores
    public List<Administrador> listarAdministradores() {
        return administradorRepository.findAll();
    }

    @Transactional
    public Administrador crearAdministrador(Administrador administrador) {
        // Verificar si el DNI ya está registrado
        if (administradorRepository.findByDni(administrador.getDni()).isPresent()) {
            throw new RuntimeException("Ya existe un administrador con este DNI");
        }

        // Verificar si el correo ya está registrado
        if (administradorRepository.findByCodigo(administrador.getCodigo()).isPresent()) {
            throw new RuntimeException("Ya existe un administrador con este correo");
        }

        // Hashear la contraseña antes de guardar
        String hashedPassword = argon2.hash(2, 65536, 1, administrador.getPassword().toCharArray());
        administrador.setPassword(hashedPassword);

        // Asignar el rol de administrador
        administrador.setRol(Rol.ADMIN);

        // Guardar el administrador en la base de datos
        return administradorRepository.save(administrador);
    }

    @Transactional
    public Administrador actualizarAdministrador(Long id, ActualizarAdministradorRequest request) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con id: " + id));

        // Verificar si el DNI ya está registrado por otro administrador
        if (request.getDni() != null && !request.getDni().isEmpty() &&
                administradorRepository.findByDni(request.getDni()).isPresent() &&
                !administrador.getDni().equals(request.getDni())) {
            throw new RuntimeException("Ya existe un administrador con este DNI");
        }

        // Verificar si el correo ya está registrado por otro administrador
        if (request.getCorreo() != null && !request.getCorreo().isEmpty() &&
                administradorRepository.findByCodigo(request.getCorreo()).isPresent() &&
                !administrador.getCodigo().equals(request.getCorreo())) {
            throw new RuntimeException("Ya existe un administrador con este correo");
        }

        // Actualizar los campos si se proporcionan
        if (request.getNombre() != null && !request.getNombre().isEmpty()) {
            administrador.setNombre(request.getNombre());
        }

        if (request.getApellido() != null && !request.getApellido().isEmpty()) {
            administrador.setApellido(request.getApellido());
        }

        if (request.getDni() != null && !request.getDni().isEmpty()) {
            administrador.setDni(request.getDni());
        }

        if (request.getTelefono() != null && !request.getTelefono().isEmpty()) {
            administrador.setTelefono(request.getTelefono());
        }

        if (request.getCorreo() != null && !request.getCorreo().isEmpty()) {
            administrador.setCodigo(request.getCorreo()); // El correo también es el código
        }

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            // Hashear la nueva contraseña
            String hashedPassword = argon2.hash(2, 65536, 1, request.getPassword().toCharArray());
            administrador.setPassword(hashedPassword);
        }

        // Guardar el administrador actualizado en la base de datos
        return administradorRepository.save(administrador);
    }


    @Transactional
    public void eliminarAdministrador(Long id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con id: " + id));


        administradorRepository.delete(administrador);
    }

}

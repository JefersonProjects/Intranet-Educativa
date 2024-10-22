package utp.edu.pe.Integrador_Backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Integrador_Backend.Entidades.ActualizarAdministradorRequest;
import utp.edu.pe.Integrador_Backend.Entidades.Administrador;
import utp.edu.pe.Integrador_Backend.Service.AdministradorService;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;


    // Obtener todos los administradores
    @GetMapping
    public ResponseEntity<List<Administrador>> listarAdministradores() {
        List<Administrador> administradores = administradorService.listarAdministradores();
        return ResponseEntity.ok(administradores);
    }

    // Crear un nuevo administrador
    @PostMapping
    public ResponseEntity<Administrador> crearAdministrador(@Valid @RequestBody Administrador administrador) {
        Administrador nuevoAdministrador = administradorService.crearAdministrador(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdministrador);
    }

    // Actualizar datos del administrador
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizarAdministrador(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarAdministradorRequest request) {

        Administrador administradorActualizado = administradorService.actualizarAdministrador(id, request);
        return ResponseEntity.ok(administradorActualizado);
    }
}

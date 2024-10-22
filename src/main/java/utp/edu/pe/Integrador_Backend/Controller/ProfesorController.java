package utp.edu.pe.Integrador_Backend.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Integrador_Backend.Entidades.AsignacionProfesor;
import utp.edu.pe.Integrador_Backend.Entidades.AsignarSubcursosRequest;
import utp.edu.pe.Integrador_Backend.Entidades.Profesor;
import utp.edu.pe.Integrador_Backend.Entidades.UpdateRequest;
import utp.edu.pe.Integrador_Backend.Service.AsignacionProfesorService;
import utp.edu.pe.Integrador_Backend.Service.ProfesorService;

import java.util.List;

@RestController
@Controller
@RequestMapping("api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private AsignacionProfesorService asignacionProfesorService;

    // Crear un nuevo profesor
    @PostMapping("/registrar")
    public ResponseEntity<Profesor> crearProfesor(@RequestBody Profesor profesor) {
        Profesor nuevoProfesor = profesorService.crearProfesor(profesor);
        return ResponseEntity.ok(nuevoProfesor);
    }

    // Listar todos los profesores
    @GetMapping("/list")
    public ResponseEntity<List<Profesor>> listarProfesores() {
        List<Profesor> profesores = profesorService.listarProfesores();
        return ResponseEntity.ok(profesores);
    }

    // Buscar profesor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerProfesorPorId(@PathVariable Long id) {
        Profesor profesor = profesorService.obtenerProfesorPorId(id);
        return ResponseEntity.ok(profesor);
    }

    // Buscar profesor por DNI
    @GetMapping("/buscarPorDni")
    public ResponseEntity<List<Profesor>> buscarProfesoresPorDni(@RequestParam(required = false) String dniPrefix) {
        if (dniPrefix == null || dniPrefix.isEmpty()) {
            // Si no se envía un prefijo, devolver todos los profesores
            return ResponseEntity.ok(profesorService.listarProfesores());
        }

        // Si se envía un prefijo, buscar por el DNI que comience con ese prefijo
        List<Profesor> profesores = profesorService.buscarProfesoresPorDniPrefix(dniPrefix);
        return ResponseEntity.ok(profesores);
    }

    // Actualizar teléfono y/o contraseña del profesor
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Profesor> actualizarProfesor(@PathVariable Long id, @RequestBody UpdateRequest request) {
        Profesor profesorActualizado = profesorService.actualizarProfesor(id, request.getTelefono(), request.getPassword());
        return ResponseEntity.ok(profesorActualizado);
    }

    // Eliminar profesor
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProfesor(@PathVariable Long id) {
        profesorService.eliminarProfesor(id);
        return ResponseEntity.ok("Profesor eliminado");
    }

    // Asignar subcurso a un profesor
    @PostMapping("/asignar")
    public ResponseEntity<?> asignarSubcursos(@RequestBody AsignarSubcursosRequest request) {
        try {
            asignacionProfesorService.asignarSubcursos(request.getUsuarioId(), request.getSubcursosIds());
            return ResponseEntity.ok("Subcursos asignados correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Desasignar subcurso de un profesor
    @DeleteMapping("/desasignar/{asignacionId}")
    public ResponseEntity<Void> desasignarSubcurso(@PathVariable Long asignacionId) {
        asignacionProfesorService.desasignarSubcurso(asignacionId);
        return ResponseEntity.noContent().build();
    }
}


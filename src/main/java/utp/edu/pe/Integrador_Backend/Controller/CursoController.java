package utp.edu.pe.Integrador_Backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.Integrador_Backend.Entidades.Curso;
import utp.edu.pe.Integrador_Backend.Service.CursoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/list")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{cursoId}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long cursoId) {
        Curso curso = cursoService.obtenerCursoPorId(cursoId);
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.crearCurso(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    @PutMapping("/update/{cursoId}")
    public ResponseEntity<Curso> actualizarCurso(
            @PathVariable Long cursoId,
            @RequestBody Map<String, String> camposActualizados) {

        String nuevoNombre = camposActualizados.get("nombreCurso");
        String nuevaDescripcion = camposActualizados.get("descripcion");

        Curso cursoActualizado = cursoService.actualizarCurso(cursoId, nuevoNombre, nuevaDescripcion);
        return ResponseEntity.ok(cursoActualizado);
    }

    @DeleteMapping("/{cursoId}")
    public ResponseEntity<String> eliminarCurso(@PathVariable Long cursoId) {
        cursoService.eliminarCurso(cursoId);
        return ResponseEntity.ok("Curso eliminado correctamente");
    }
}

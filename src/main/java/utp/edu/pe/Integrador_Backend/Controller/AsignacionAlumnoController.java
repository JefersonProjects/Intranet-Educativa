package utp.edu.pe.Integrador_Backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.pe.Integrador_Backend.Entidades.AsignacionAlumno;
import utp.edu.pe.Integrador_Backend.Service.AsignacionAlumnoService;
@RestController
@RequestMapping("api/asignaciones-alumno")
public class AsignacionAlumnoController {

    @Autowired
    private AsignacionAlumnoService asignacionAlumnoService;

    @PostMapping("/alumno/{alumnoId}/subcurso/{subcursoId}")
    public ResponseEntity<AsignacionAlumno> asignarAlumnoASubcurso(
            @PathVariable Long alumnoId, @PathVariable Long subcursoId) {
        AsignacionAlumno asignacion = asignacionAlumnoService.asignarAlumnoASubcurso(alumnoId, subcursoId);
        return ResponseEntity.ok(asignacion);
    }
}

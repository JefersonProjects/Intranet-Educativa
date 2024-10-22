package utp.edu.pe.Integrador_Backend.Service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Integrador_Backend.Entidades.Alumno;
import utp.edu.pe.Integrador_Backend.Entidades.AsignacionAlumno;
import utp.edu.pe.Integrador_Backend.Entidades.Subcurso;
import utp.edu.pe.Integrador_Backend.Repository.AlumnoRepository;
import utp.edu.pe.Integrador_Backend.Repository.AsignacionAlumnoRepository;
import utp.edu.pe.Integrador_Backend.Repository.SubcursoRepository;

@Service
public class AsignacionAlumnoService {
    @Autowired
    private AsignacionAlumnoRepository asignacionAlumnoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private SubcursoRepository subcursoRepository;


    @Transactional
    public AsignacionAlumno asignarAlumnoASubcurso(Long alumnoId, Long subcursoId) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con id: " + alumnoId));
        Subcurso subcurso = subcursoRepository.findById(subcursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Subcurso no encontrado con id: " + subcursoId));

        // Verificar que el nivel del alumno y del subcurso coincidan
        if (!alumno.getNivel().equals(subcurso.getNivel())) {
            throw new IllegalArgumentException("El nivel del alumno y del subcurso no coinciden.");
        }

        AsignacionAlumno asignacion = new AsignacionAlumno();
        asignacion.setAlumno(alumno);
        asignacion.setSubcurso(subcurso);

        return asignacionAlumnoRepository.save(asignacion);
    }
}

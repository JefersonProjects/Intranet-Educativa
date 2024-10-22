package utp.edu.pe.Integrador_Backend.Service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Integrador_Backend.Entidades.Alumno;
import utp.edu.pe.Integrador_Backend.Entidades.AsignacionAlumno;
import utp.edu.pe.Integrador_Backend.Entidades.Curso;
import utp.edu.pe.Integrador_Backend.Entidades.Subcurso;
import utp.edu.pe.Integrador_Backend.Repository.*;

import java.util.List;

@Service
public class SubcursoService {

    @Autowired
    private AsignacionProfesorRepository asignacionProfesorRepository;
    @Autowired
    private AsignacionAlumnoRepository asignacionAlumnoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private SubcursoRepository subcursoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    private static final Logger logger = LoggerFactory.getLogger(SubcursoService.class);


    public List<Subcurso> listarSubcursos() {
        return subcursoRepository.findAll();
    }

    @Transactional
    public Subcurso crearSubcurso(Long cursoId, Subcurso subcurso){
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + cursoId));

        logger.info("Creando subcurso: {} en curso ID: {} a las {}", subcurso.getNombreSubcurso(), cursoId, new java.util.Date());

        if (subcursoRepository.existsByNombreSubcursoIgnoreCaseAndNivel(subcurso.getNombreSubcurso(), curso.getNivel())) {
            throw new RuntimeException("Ya existe un subcurso con el nombre " + subcurso.getNombreSubcurso() + " en el nivel " + curso.getNivel());
        }

        subcurso.setCurso(curso);
        subcurso.setNivel(curso.getNivel());
        Subcurso nuevoSubcurso = subcursoRepository.save(subcurso);

        // Asignar el nuevo subcurso a los alumnos del mismo nivel
        asignarSubcursoANivel(nuevoSubcurso);

        logger.info("Subcurso creado: ID: {}, Nombre: {}, a las {}", nuevoSubcurso.getSubcursoId(), nuevoSubcurso.getNombreSubcurso(), new java.util.Date());
        return nuevoSubcurso;
    }


    public Subcurso obtenerSubcursoPorId(Long subcursoId) {
        return subcursoRepository.findById(subcursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Subcurso no encontrado con id: " + subcursoId));
    }

    // Metodo para asignar el nuevo subcurso a todos los alumnos del mismo nivel
    private void asignarSubcursoANivel(Subcurso subcurso) {
        List<Alumno> alumnosDelMismoNivel = alumnoRepository.findByNivel(subcurso.getNivel());

        for (Alumno alumno : alumnosDelMismoNivel) {
            AsignacionAlumno asignacion = new AsignacionAlumno();
            asignacion.setAlumno(alumno);
            asignacion.setSubcurso(subcurso);
            asignacionAlumnoRepository.save(asignacion);
        }
    }

    @Transactional
    public Subcurso actualizarSubcurso(Long subcursoId, String nuevoNombre, String nuevaDescripcion) {
        Subcurso subcurso = subcursoRepository.findById(subcursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Subcurso no encontrado con id: " + subcursoId));

        logger.info("Actualizando subcurso ID: {}, a las {}", subcursoId, new java.util.Date());

        // Actualizar solo el nombre y la descripción
        subcurso.setNombreSubcurso(nuevoNombre);
        subcurso.setDescripcion(nuevaDescripcion);

        logger.info("Subcurso actualizado: ID: {}, Nombre: {}, a las {}", subcurso.getSubcursoId(), subcurso.getNombreSubcurso(), new java.util.Date());

        return subcursoRepository.save(subcurso);
    }

    @Transactional
    public void eliminarSubcurso(Long subcursoId) {
        Subcurso subcurso = subcursoRepository.findById(subcursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Subcurso no encontrado con id: " + subcursoId));

        // Eliminar las asignaciones de alumnos
        asignacionAlumnoRepository.deleteBySubcurso(subcurso);

        // Eliminar las asignaciones de profesores
        asignacionProfesorRepository.deleteBySubcurso(subcurso);

        // Finalmente, eliminar el subcurso
        subcursoRepository.delete(subcurso);

        logger.info("Subcurso eliminado: ID: {}, Nombre: {}, a las {}", subcurso.getSubcursoId(), subcurso.getNombreSubcurso(), new java.util.Date());

    }
}

package utp.edu.pe.Integrador_Backend.Service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Integrador_Backend.Entidades.Alumno;
import utp.edu.pe.Integrador_Backend.Entidades.Nota;
import utp.edu.pe.Integrador_Backend.Entidades.Subcurso;
import utp.edu.pe.Integrador_Backend.Repository.AlumnoRepository;
import utp.edu.pe.Integrador_Backend.Repository.AsignacionAlumnoRepository;
import utp.edu.pe.Integrador_Backend.Repository.NotaRepository;
import utp.edu.pe.Integrador_Backend.Repository.SubcursoRepository;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AsignacionAlumnoRepository asignacionAlumnoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private SubcursoRepository subcursoRepository;

    private static final Logger logger = LoggerFactory.getLogger(NotaService.class);


    @Transactional
    public Nota registrarNota(Long alumnoId, Long subcursoId, Nota nota) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con id: " + alumnoId));
        Subcurso subcurso = subcursoRepository.findById(subcursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Subcurso no encontrado con id: " + subcursoId));

        // Verificar que el alumno esté asignado al subcurso
        boolean estaAsignado = asignacionAlumnoRepository.existsByAlumnoAndSubcurso(alumno, subcurso);
        if (!estaAsignado) {
            throw new IllegalArgumentException("El alumno no está asignado al subcurso.");
        }

        nota.setAlumno(alumno);
        nota.setSubcurso(subcurso);

        return notaRepository.save(nota);
    }

    // Actualizar una nota
    @Transactional
    public Nota actualizarNota(Long notaId, Nota nuevaNota) {
        Nota nota = notaRepository.findById(notaId)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada con id: " + notaId));

        nota.setUnidad(nuevaNota.getUnidad());
        nota.setCalificacion(nuevaNota.getCalificacion());
        logger.info("Nota actualizada con id {} a las {}", notaId, new java.util.Date());
        return notaRepository.save(nota);
    }

    // Eliminar una nota
    @Transactional
    public void eliminarNota(Long notaId) {
        Nota nota = notaRepository.findById(notaId)
                .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada con id: " + notaId));

        notaRepository.delete(nota);
        logger.info("Nota eliminada con id {} a las {}", notaId, new java.util.Date());

    }
}

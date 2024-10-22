package utp.edu.pe.Integrador_Backend.Service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.Integrador_Backend.Entidades.AsignacionProfesor;
import utp.edu.pe.Integrador_Backend.Entidades.Profesor;
import utp.edu.pe.Integrador_Backend.Entidades.Subcurso;
import utp.edu.pe.Integrador_Backend.Repository.AsignacionProfesorRepository;
import utp.edu.pe.Integrador_Backend.Repository.ProfesorRepository;
import utp.edu.pe.Integrador_Backend.Repository.SubcursoRepository;

import java.util.List;

@Service
public class AsignacionProfesorService {

    private static final Logger logger = LoggerFactory.getLogger(AsignacionProfesorService.class);

    @Autowired
    private AsignacionProfesorRepository asignacionProfesorRepository;

    @Autowired
    private SubcursoRepository subcursoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Transactional
    public void asignarSubcursos(Long profesorId, List<Long> subcursosIds) {
        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        for (Long subcursoId : subcursosIds) {
            if (asignacionProfesorRepository.existsBySubcurso_SubcursoId(subcursoId)) {
                Profesor profesorAsignado = asignacionProfesorRepository.findBySubcurso_SubcursoId(subcursoId)
                        .orElseThrow(() -> new RuntimeException("Subcurso no encontrado"))
                        .getProfesor();
                throw new RuntimeException("El subcurso " + subcursoId + " ya está asignado al profesor " + profesorAsignado.getNombre());
            }

            Subcurso subcurso = subcursoRepository.findById(subcursoId)
                    .orElseThrow(() -> new RuntimeException("Subcurso no encontrado"));

            AsignacionProfesor asignacion = new AsignacionProfesor();
            asignacion.setProfesor(profesor);
            asignacion.setSubcurso(subcurso);
            asignacionProfesorRepository.save(asignacion);
        }
    }

    @Transactional
    public void desasignarSubcurso(Long asignacionId) {

        logger.info("Eliminando asignación con ID: {} a las {}", asignacionId, new java.util.Date());
        AsignacionProfesor asignacion = asignacionProfesorRepository.findById(asignacionId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        // Al eliminar la asignación, el subcurso queda libre
        asignacionProfesorRepository.delete(asignacion);
        logger.info("Asignación eliminada con ID: {} a las {}", asignacionId, new java.util.Date());
    }
}

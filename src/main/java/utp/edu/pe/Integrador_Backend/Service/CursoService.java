package utp.edu.pe.Integrador_Backend.Service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.Integrador_Backend.Entidades.Curso;
import utp.edu.pe.Integrador_Backend.Entidades.Nivel;
import utp.edu.pe.Integrador_Backend.Repository.CursoRepository;

import java.util.List;
@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    private static final Logger logger = LoggerFactory.getLogger(CursoService.class);


    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso obtenerCursoPorId(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + cursoId));
    }

    @Transactional
    public Curso crearCurso(Curso curso) {
        logger.info("Creando curso: {} de nivel {}, a las {}", curso.getNombreCurso(), curso.getNivel(), new java.util.Date());
        validarNivel(curso.getNivel());  // Validar el nivel antes de guardar
        Curso nuevoCurso = cursoRepository.save(curso);
        logger.info("Curso creado: ID: {}, Nombre: {}, a las {}", nuevoCurso.getCursoId(), nuevoCurso.getNombreCurso(), new java.util.Date());
        return nuevoCurso;
    }

    @Transactional
    public Curso actualizarCurso(Long cursoId, String nuevoNombre, String nuevaDescripcion) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + cursoId));

        logger.info("Actualizando curso ID: {}, a las {}", cursoId, new java.util.Date());
        // Actualizar solo el nombre y la descripción
        curso.setNombreCurso(nuevoNombre);
        curso.setDescripcion(nuevaDescripcion);
        cursoRepository.save(curso);
        logger.info("Curso actualizado: ID: {}, Nombre: {}, a las {}", curso.getCursoId(), curso.getNombreCurso(), new java.util.Date());
        return curso;
    }

    @Transactional
    public void eliminarCurso(Long cursoId) {
        Curso curso = obtenerCursoPorId(cursoId);
        cursoRepository.delete(curso);
        logger.info("Curso eliminado: ID: {}, Nombre: {}, a las {}", curso.getCursoId(), curso.getNombreCurso(), new java.util.Date());
    }

    private void validarNivel(Nivel nivel) {
        if (nivel == null || (!nivel.equals(Nivel.PRIMARIA) && !nivel.equals(Nivel.SECUNDARIA))) {
            throw new IllegalArgumentException("Nivel inválido. Debe ser PRIMARIA o SECUNDARIA.");
        }
    }
}

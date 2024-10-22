package utp.edu.pe.Integrador_Backend.Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignarSubcursosRequest {
    private Long usuarioId;  // ID del profesor
    private List<Long> subcursosIds;  // Lista de IDs de subcursos
}

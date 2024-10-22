package utp.edu.pe.Integrador_Backend.Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarAdministradorRequest {
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String correo;
    private String password;
}

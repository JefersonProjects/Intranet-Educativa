package utp.edu.pe.Integrador_Backend.Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRequest {
    private String telefono;
    private String password;
}

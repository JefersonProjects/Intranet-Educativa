package utp.edu.pe.Integrador_Backend.Authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
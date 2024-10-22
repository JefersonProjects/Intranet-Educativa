package utp.edu.pe.Integrador_Backend.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Table(name ="SOP_USUARIO_ADMINISTRADOR")
public class Administrador extends Usuario {

    // Alias para el campo 'codigo' del padre, usando 'correo' en la clase Administrador
    @Transient  // No persistimos 'correo', ya que se almacenará como 'codigo' en la tabla 'usuario'
    private String correo;

    public String getCorreo() {
        return getCodigo();
    }

    public void setCorreo(String correo) {
        setCodigo(correo);
    }
    @Override
    public String getPassword() {
        return super.getPassword();
    }
}

package hn.emeka.tarea1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosDTO {
    private String codigoUsuario;
    private String nombre;
    private String apellido;
    private String departamento;
    private String correo;
    private String telefono;
    private int idPerfil;
}

package hn.emeka.tarea1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @Column(name = "codigousuario")
    private String codigoUsuario;
    private String nombre;
    private String apellido;
    private String departamento;
    private String correo;
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy fetch para cargar perfil solo cuando se necesite
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil") // Clave foránea en usuarios apuntando a idPerfil en perfil
    private Perfil perfil;
}

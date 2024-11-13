package hn.emeka.tarea1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.emeka.tarea1.dtos.UsuariosDTO;
import hn.emeka.tarea1.models.Usuarios;
import hn.emeka.tarea1.repository.PerfilRepository;
import hn.emeka.tarea1.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    private static ModelMapper modelMapper;

    public static ModelMapper getModelMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }

    public List<UsuariosDTO> obtenerUsuarios() {
        List<Usuarios> usuarios = usuarioRepository.findAll();
        getModelMapper();
        // Convertimos la lista de Usuarios a una lista de UsuariosDTO
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuariosDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<UsuariosDTO> obtenerUsuarioPorCodigo(String codigoUsuario) {
        getModelMapper();
        Optional<Usuarios> usuarioBD = usuarioRepository.findById(codigoUsuario);
        UsuariosDTO usuarioDTO = this.modelMapper.map(usuarioBD, UsuariosDTO.class);
        return Optional.ofNullable(usuarioDTO);
    }

    public String guardarUsuario(UsuariosDTO usuarioDTO) {

        getModelMapper();
        Usuarios usuarioBD = this.modelMapper.map(usuarioDTO, Usuarios.class);
        try {
            if (this.usuarioRepository.existsById(usuarioBD.getCodigoUsuario())) {
                return "El codigo: " + usuarioDTO.getCodigoUsuario() + " ya existe en la base de datos use otro codigo para ingresarlo al sistema";
            }
            this.usuarioRepository.save(usuarioBD);
            return "El usuario: " + usuarioBD.getNombre() + " con codigo: " + usuarioBD.getCodigoUsuario() + " ha sido ingresado al sistema";
        } catch (Exception e) {

            return "Ha habido un error: " + e;
        }

    }

    public String actualizarUsuario(String codigoUsuario, UsuariosDTO usuariosDTO) {
        try {
            if (!this.usuarioRepository.existsById(codigoUsuario)) {
                return "El usuario con codigo: " + codigoUsuario + " No existe en la base de datos, favor usar otro metodo para insertarlo";
            }
            getModelMapper();
            usuariosDTO.setCodigoUsuario(codigoUsuario);
            Usuarios usuarioBD = this.modelMapper.map(usuariosDTO, Usuarios.class);
            this.usuarioRepository.save(usuarioBD);
            return "El usuario: " + usuarioBD.getCodigoUsuario() + " ha sido actualizado";

        } catch (Exception e) {

            return "Ha habido un error: " + e;
        }
    }

    public String crearUsuarioPerfil(UsuariosDTO usuarioDTO) {

        // Verificar que el perfil existe antes de crear el usuario
        if (!this.perfilRepository.existsById(usuarioDTO.getIdPerfil())) {
            return "El perfil especificado no existe en el sistema. Por favor, crea el perfil antes de asignarlo al usuario.";
        }
        
        getModelMapper();
        Usuarios nuevoUsuario = this.modelMapper.map(usuarioDTO, Usuarios.class);
        this.usuarioRepository.save(nuevoUsuario);

        return "Usuario guardado en el sistema";

    }

    public String eliminarUsuarioPerfil(String codigoUsuario) {
        if (!this.usuarioRepository.existsById(codigoUsuario)) {
            return "El usuario con el codigo: " + codigoUsuario + " no existe o ya se ha eliminado del sistema";
        }

        this.usuarioRepository.deleteById(codigoUsuario);
        return "El usuario ha sido eliminado";
    }

}

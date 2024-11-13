package hn.emeka.tarea1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.emeka.tarea1.dtos.UsuariosDTO;
import hn.emeka.tarea1.service.UsuariosService;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;

    @GetMapping("/")
    public List<UsuariosDTO> obtenerUsuarios() {
        return this.usuarioService.obtenerUsuarios();
    }

    @GetMapping("{codigoUsuario}")
    public Optional<UsuariosDTO> obtenerUsuarioPorCodigo(@PathVariable String codigoUsuario) {
        return this.usuarioService.obtenerUsuarioPorCodigo(codigoUsuario);
    }

    @PostMapping("/crear")
    public String guardarUsuario(@RequestBody UsuariosDTO usuarioDTO) {

        return this.usuarioService.guardarUsuario(usuarioDTO);
    }

    @PutMapping("/actualizar/{codigoUsuario}")
    public String actualizarUsuario(@PathVariable String codigoUsuario, @RequestBody UsuariosDTO usuarioDTO) {

        return this.usuarioService.actualizarUsuario(codigoUsuario, usuarioDTO);
    }

    @PostMapping("/crearUsuarioPerfil")
    public String crearUsuarioPerfil(@RequestBody UsuariosDTO usuarioDTO) {
               
        return this.usuarioService.crearUsuarioPerfil(usuarioDTO);
    }

    @DeleteMapping("/eliminarUsuarioPerfil/{codigoUsuario}")
    public String eliminarUsuarioPerfil(@PathVariable String codigoUsuario){
        return this.usuarioService.eliminarUsuarioPerfil(codigoUsuario);
    }
    
}

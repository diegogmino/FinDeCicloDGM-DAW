package com.diego.findeciclo.controller;

import java.util.List;
import com.diego.findeciclo.dto.UsuarioDTO;
import com.diego.findeciclo.mapper.UsuarioMapper;
import com.diego.findeciclo.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuarioService usuarioService;

    // MÉTODOS GET
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarUsuarios(Model model) {

        List<UsuarioDTO> usuariosDTO = UsuarioMapper.INSTANCE.toListUsuarioDTO(usuarioService.buscarTodos());

        model.addAttribute("usuarios", usuariosDTO);
        return "usuarios/listadoUsuarios";

    }

}

package com.diego.findeciclo.controller;

import java.util.List;
import com.diego.findeciclo.model.Perfil;
import com.diego.findeciclo.model.Usuario;
import com.diego.findeciclo.service.IPerfilService;
import com.diego.findeciclo.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/perfiles")
public class PerfilController {
    
    @Autowired
    private IPerfilService perfilService;

    @Autowired
    private IUsuarioService usuarioService;

    // MÉTODOS GET
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarPerfiles(Model model) {

        List<Perfil> perfiles = perfilService.buscarTodos();

        model.addAttribute("alerta", false);
        model.addAttribute("perfiles", perfiles);
        return "perfiles/listadoPerfiles";

    }
    
    @RequestMapping(value = "/nuevo", method = RequestMethod.GET)
    public String mostrarFormularioPerfiles() {

        return "perfiles/formularioPerfil";

    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String mostrarFormularioEditarPerfiles(@PathVariable int id, Model model) {

        Perfil perfil = perfilService.buscarPerfil(id);
        model.addAttribute("perfil", perfil);
        return "perfiles/formularioEditarPerfil";

    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable int id, Model model) {

        List<Usuario> usuarios = usuarioService.buscarTodos();
        Perfil perfil = perfilService.buscarPerfil(id);

        boolean tieneUsuario = false;
        for(Usuario usuario : usuarios) {
            if(usuario.getPerfil().equals(perfil)) {
                tieneUsuario = true;
                break;
            }
        }

        if(!tieneUsuario) {
            perfilService.eliminarPerfil(id);
            return "redirect:/admin/perfiles/index";
        }

        List<Perfil> perfiles = perfilService.buscarTodos();

        model.addAttribute("alerta", true);
        model.addAttribute("perfiles", perfiles);
        model.addAttribute("perfil", perfil);
        return "perfiles/listadoPerfiles";
    }

    // MÉTODOS POST
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(@RequestParam("perfil") String perfil, Model model) {

        Perfil perfilNuevo = new Perfil(perfil);

        perfilService.guardarPerfil(perfilNuevo);

		return "redirect:/admin/perfiles/index";
        
	}

    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    public String actualizar(@RequestParam("id") int id, @RequestParam("perfil") String perfil, Model model) {

        Perfil perfilBBDD = perfilService.buscarPerfil(id);
        perfilBBDD.setPerfil(perfil);
        perfilService.guardarPerfil(perfilBBDD);

		return "redirect:/admin/perfiles/index";
        
	}

}

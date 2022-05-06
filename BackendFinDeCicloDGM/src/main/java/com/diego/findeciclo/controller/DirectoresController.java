package com.diego.findeciclo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.service.IDirectorService;
import com.diego.findeciclo.service.IPeliculaService;
import com.diego.findeciclo.specification.DirectorSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/directores")
public class DirectoresController {
    
    @Autowired
    private IDirectorService directorService;

    @Autowired
    private IPeliculaService peliculaService;


    // MÉTODOS GET
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarDirectoresString(Model model) {

        List<Director> directores = directorService.buscarTodos();

        model.addAttribute("alerta", false);
        model.addAttribute("directores", directores);
        return "directores/listadoDirectores";

    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.GET)
    public String mostrarFormularioPelicula() {

        return "directores/formularioDirector";
    
    }
    
    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String mostrarFormularioEditarPelicula(Model model, @PathVariable int id) {

        Director director = directorService.buscarPorId(id);
        
        model.addAttribute("director", director);

        return "directores/formularioEditarDirector";
    
    }

    @RequestMapping(value = "/detalle/{id}", method = RequestMethod.GET)
    public String mostrarDetalleDirector(Model model, @PathVariable int id) {

        Director director = directorService.buscarPorId(id);

        model.addAttribute("director", director);

        return "directores/detalleDirector";
    
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable int id, Model model) {

        Director director = directorService.buscarPorId(id);
        List<Pelicula> peliculas = peliculaService.buscarTodas();

        boolean tienePelicula = false;

        for(Pelicula pelicula : peliculas) {
            if(pelicula.getDirectores().contains(director)) {
                tienePelicula = true;
                break;
            }
        }

        if(!tienePelicula) {
            // Eliminar la portada asociada a la pelicula
            try {
                String ruta = "src\\main\\resources\\static\\images\\fotos_directores\\"+ director.getFoto();
                Files.delete(Paths.get(ruta));
            } catch (IOException e) {
                e.printStackTrace();
            }

            directorService.eliminarDirector(id);

            return "redirect:/admin/directores/index";

        }

        List<Director> directores = directorService.buscarTodos();

        model.addAttribute("alerta", true);
        model.addAttribute("director", director);
        model.addAttribute("directores", directores);

        return "directores/listadoDirectores";
    
    }

    // Filtro
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public String filtrar(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido, @RequestParam(required = false) String pais, Model model) {

		Specification<Director> spec = construirSpecification(nombre, apellido, pais);
        List<Director> directores = directorService.filtrar(spec);

        model.addAttribute("directores", directores);

		return "directores/listadoDirectores";
		
	}

    // MÉTODOS POST
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, 
    @RequestParam("pais") String pais, @RequestParam(name="foto", required = false) String foto, Model model) {

        String fotoGuardada = descargarImagen(foto, nombre, apellido);
        Director director = new Director(nombre, apellido, pais, fotoGuardada);
        directorService.guardarDirector(director);

		return "redirect:/admin/directores/index";
        
	}

    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    public String actualizar(@RequestParam("id") int id, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, 
    @RequestParam("pais") String pais, @RequestParam(name="foto", required = false) String foto, Model model) {

        Director directorBBDD = directorService.buscarPorId(id);

        directorBBDD.setNombre(nombre);
        directorBBDD.setApellido(apellido);
        directorBBDD.setPais(pais);

        // Trabajamos con la fotografía para ver si se ha cambiado o no
        if(directorBBDD.getFoto().equals(foto)) {
            directorBBDD.setFoto(foto);
        } else {
            // Eliminar la portada asociada a la pelicula
            try {
                String ruta = "src\\main\\resources\\static\\images\\fotos_directores\\"+ directorBBDD.getFoto();
                Files.delete(Paths.get(ruta));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String nuevaFoto = descargarImagen(foto, nombre, apellido);
            directorBBDD.setFoto(nuevaFoto);
        }

        directorService.guardarDirector(directorBBDD);

		return "redirect:/admin/directores/index";

	}

    // Métodos privados
    private String descargarImagen(String url, String nombre, String apellido) {
		
        nombre = nombre.toLowerCase();
        apellido = apellido.toLowerCase();

		String ruta = "src\\main\\resources\\static\\images\\fotos_directores\\"+ apellido + "_" + nombre +".jpeg";
		
		try {
			InputStream in = new URL(url).openStream();
			Files.copy(in, Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);
			return apellido+"_"+nombre+".jpeg";
		} catch (IOException e) {
			return null;
		}
		
	}

    private Specification<Director> construirSpecification(String nombre, String apellido, String pais) {
		
		// Seteamos el objeto spec con la cláusula where a null para que de forma predeterminada haga un findAll normal
        Specification<Director> spec = Specification.where(null);

        if(nombre != "") {
            spec = spec.and(DirectorSpecification.nombre(nombre));
        }

        if(apellido != "") {
            spec = spec.and(DirectorSpecification.apellido(apellido));
        }
        
        if(pais != "") {
            spec = spec.and(DirectorSpecification.pais(pais));
        }
        
		return spec;
	}

    

}

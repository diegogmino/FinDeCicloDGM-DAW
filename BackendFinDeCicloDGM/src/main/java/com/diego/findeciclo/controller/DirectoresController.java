package com.diego.findeciclo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.service.IDirectorService;
import com.diego.findeciclo.service.IPeliculaService;
import com.diego.findeciclo.service.cloudinary.CloudinaryService;
import com.diego.findeciclo.specification.DirectorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/admin/directores")
public class DirectoresController {
    
    @Autowired
    private IDirectorService directorService;

    @Autowired
    private IPeliculaService peliculaService;

    @Autowired
    private CloudinaryService cloudinaryService;


    // MÉTODOS GET
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarDirectores(Model model) {

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
    @RequestParam("pais") String pais, @RequestParam("foto") MultipartFile foto, Model model) throws IOException {

        Map result = cloudinaryService.upload(foto);
        String fotoCloudinary = (String) result.get("url");
        String foto_id = (String) result.get("public_id");

        Director director = new Director(nombre, apellido, pais, fotoCloudinary, foto_id);
        directorService.guardarDirector(director);

		return "redirect:/admin/directores/index";
        
	}

    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    public String actualizar(@RequestParam("id") int id, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, 
    @RequestParam("pais") String pais, @RequestParam(name="foto", required = false) MultipartFile foto, Model model) throws IOException {

        Director directorBBDD = directorService.buscarPorId(id);

        directorBBDD.setNombre(nombre);
        directorBBDD.setApellido(apellido);
        directorBBDD.setPais(pais);

        if(!foto.isEmpty()) {

            if(directorBBDD.getFoto_id() != null) {
                cloudinaryService.delete(directorBBDD.getFoto_id());
            }

            Map result = cloudinaryService.upload(foto);

            String fotoCloudinary = (String) result.get("url");
            String foto_id = (String) result.get("public_id");

            directorBBDD.setFoto(fotoCloudinary);
            directorBBDD.setFoto_id(foto_id);

        }

        directorService.guardarDirector(directorBBDD);

		return "redirect:/admin/directores/index";

	}

    // Métodos privados
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

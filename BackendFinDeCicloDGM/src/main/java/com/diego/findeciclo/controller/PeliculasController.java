package com.diego.findeciclo.controller;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.model.Formato;
import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.service.IDirectorService;
import com.diego.findeciclo.service.IPeliculaService;
import com.diego.findeciclo.specification.PeliculaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/peliculas")
public class PeliculasController {
    
    @Autowired
	private IPeliculaService peliculaService;

    @Autowired
    private IDirectorService directorService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarPeliculas(Model model) {

        List<Pelicula> peliculas = peliculaService.buscarTodas();
        model.addAttribute("peliculas", peliculas);
        return "peliculas/listadoPeliculas";
    
    }

    @RequestMapping(value = "/nueva", method = RequestMethod.GET)
    public String mostrarFormularioPelicula(Model model) {

        List<Director> directores = directorService.buscarTodos();
        List<String> generos = crearListaGeneros();
        List<String> formatos = crearListaFormatos();

        model.addAttribute("generos", generos);
        model.addAttribute("formatos", formatos);
        model.addAttribute("directores", directores);
        return "peliculas/formularioPelicula";
    
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(@RequestParam("codigoBarras") long codigoBarras, @RequestParam("titulo") String titulo, 
    @RequestParam("director1") Integer dire1, @RequestParam(name="director2", required = false) Integer dire2, @RequestParam("precio") BigDecimal precio,
    @RequestParam("portada") String portada, @RequestParam("destacada") boolean destacada, @RequestParam("unidades") Integer unidades, 
    @RequestParam("genero") String genero, @RequestParam("formato") Formato formato, @RequestParam("sinopsis") String sinopsis, Model model) {

        List<Director> directores = new ArrayList<Director>();

        if(dire1 != null) {
            directores.add(directorService.buscarPorId(dire1));
        }

        if(dire2 != null) {
            directores.add(directorService.buscarPorId(dire2));
        }

        String portadaGuardada = descargarImagen(portada, titulo, codigoBarras);
        Pelicula pelicula = new Pelicula(codigoBarras, titulo, precio, portadaGuardada, destacada, unidades, genero, formato, sinopsis, directores);
        peliculaService.guardarPeli(pelicula);

		return "redirect:/admin/peliculas/index";
        
	}

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String mostrarFormularioEditarPelicula(Model model, @PathVariable int id) {

        Pelicula peli = peliculaService.buscarPorId(id);
        List<Director> directores = directorService.buscarTodos();
        List<String> generos = crearListaGeneros();
        List<String> formatos = crearListaFormatos();

        directores.removeAll(peli.getDirectores());
        generos.remove(peli.getGenero());
        formatos.remove(peli.getFormato().toString());

        model.addAttribute("pelicula", peli);
        model.addAttribute("generos", generos);
        model.addAttribute("formatos", formatos);
        model.addAttribute("directores", directores);

        return "peliculas/formularioEditarPelicula";
    
    }

    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    public String actualizar(@RequestParam("id") int id, @RequestParam("codigoBarras") long codigoBarras, @RequestParam("titulo") String titulo, 
    @RequestParam("director1") Integer dire1, @RequestParam(name="director2", required = false) Integer dire2, @RequestParam("precio") BigDecimal precio,
    @RequestParam("portada") String portada, @RequestParam("destacada") boolean destacada, @RequestParam("unidades") Integer unidades, 
    @RequestParam("genero") String genero, @RequestParam("formato") Formato formato, @RequestParam("sinopsis") String sinopsis, Model model) {

        Pelicula peliculaBBDD = peliculaService.buscarPorId(id);

        List<Director> directores = new ArrayList<Director>();

        if(dire1 != null) {
            directores.add(directorService.buscarPorId(dire1));
        }

        if(dire2 != null) {
            directores.add(directorService.buscarPorId(dire2));
        }

        peliculaBBDD.setCodigoBarras(codigoBarras);
        peliculaBBDD.setTitulo(titulo);
        peliculaBBDD.setDirectores(directores);
        peliculaBBDD.setPrecio(precio);

        // Trabajamos con la portada para ver si se ha cambiado o no
        if(peliculaBBDD.getPortada().equals(portada)) {
            peliculaBBDD.setPortada(portada);
        } else {
            // Eliminar la portada asociada a la pelicula
            try {
                Files.delete(Paths.get(peliculaBBDD.getPortada()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String portadaNueva = descargarImagen(portada, titulo, codigoBarras);
            peliculaBBDD.setPortada(portadaNueva);
        }

        peliculaBBDD.setDestacada(destacada);
        peliculaBBDD.setUnidades(unidades);
        peliculaBBDD.setGenero(genero);
        peliculaBBDD.setFormato(formato);
        peliculaBBDD.setSinopsis(sinopsis);

        peliculaService.guardarPeli(peliculaBBDD);

		return "redirect:/admin/peliculas/index";

	}

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminarPelicula(@PathVariable int id, Model model) {

        Pelicula peli = peliculaService.buscarPorId(id);

        // Eliminar la portada asociada a la pelicula
        try {
            Files.delete(Paths.get(peli.getPortada()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        peliculaService.eliminarPeli(id);

        return "redirect:/admin/peliculas/index";
    
    }


    // Filtro
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public String filtrar(@RequestParam(required = false) String destacada, @RequestParam(required = false) Formato formato, @RequestParam(required = false) String titulo, @RequestParam(required = false) String genero, Model model) {

        if(destacada.equals("null")) {
            destacada = null;
        }

		Specification<Pelicula> spec = construirSpec(destacada, formato, titulo, genero);
        List<Pelicula> peliculas = peliculaService.filtrar(spec);

		model.addAttribute("peliculas", peliculas);

		return "peliculas/listadoPeliculas";
		
	}

    // Métodos privados

    private String descargarImagen(String url, String titulo, long codigoBarras) {
		
		String ruta = "img\\portadas_pelis\\"+codigoBarras+"_"+titulo+".jpeg";
		
		try {
			InputStream in = new URL(url).openStream();
			Files.copy(in, Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);
			return ruta;
		} catch (IOException e) {
			return null;
		}
		
	}

    private List crearListaGeneros() {

        ArrayList<String> generos = new ArrayList<String>();

        generos.add("Acción");
        generos.add("Animación");
        generos.add("Anime");
        generos.add("Aventuras");
        generos.add("Bélico");
        generos.add("Ciencia ficción");
        generos.add("Comedia");
        generos.add("Drama");
        generos.add("Fantástico");
        generos.add("Histórico");
        generos.add("Policíaca");
        generos.add("Suspense");
        generos.add("Terror");
        generos.add("Thriller");
        generos.add("Western");

        return generos;

    }

    private List crearListaFormatos() {

        ArrayList<String> formatos = new ArrayList<String>();

        formatos.add("DVD");
        formatos.add("Bluray");
        formatos.add("UHD4K");

        return formatos;

    }

    private Specification<Pelicula> construirSpec(String destacada, Formato formato, String titulo, String genero) {
		
		// Seteamos el objeto spec con la cláusula where a null para que de forma predeterminada haga un findAll normal
        Specification<Pelicula> spec = Specification.where(null);

        if(destacada != null) {
            boolean valor = false;
            if(destacada.equals("Si")) {
                valor = true;
            }
            spec = spec.and(PeliculaSpecification.destacada(valor));
        }

        if(formato != null) {
            spec = spec.and(PeliculaSpecification.formato(formato));
        }
        
        if(titulo != null) {
            spec = spec.and(PeliculaSpecification.titulo(titulo));
        }

        if(genero != null) {
            spec = spec.and(PeliculaSpecification.genero(genero));
        }
        
		return spec;
	}

}

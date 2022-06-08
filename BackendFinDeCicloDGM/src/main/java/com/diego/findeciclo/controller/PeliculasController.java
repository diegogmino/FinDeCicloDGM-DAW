package com.diego.findeciclo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.model.Formato;
import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.service.IDirectorService;
import com.diego.findeciclo.service.IPeliculaService;
import com.diego.findeciclo.service.cloudinary.CloudinaryService;
import com.diego.findeciclo.specification.PeliculaSpecification;
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
@RequestMapping(value = "/admin/peliculas")
public class PeliculasController {

    @Autowired
    private IPeliculaService peliculaService;

    @Autowired
    private IDirectorService directorService;

    @Autowired
    private CloudinaryService cloudinaryService;

    // MÉTODOS GET
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarPeliculas(Model model) {

        List<Pelicula> peliculas = peliculaService.buscarTodas();

        List<String> generos = crearListaGeneros();
        List<String> formatos = crearListaFormatos();

        model.addAttribute("peliculas", peliculas);
        model.addAttribute("generos", generos);
        model.addAttribute("formatos", formatos);

        return "peliculas/listadoPeliculas";

    }

    @RequestMapping(value = "/nueva", method = RequestMethod.GET)
    public String mostrarFormularioPelicula(Model model) {

        List<Director> directores = directorService.buscarTodos();
        List<String> generos = crearListaGeneros();
        List<String> formatos = crearListaFormatos();

        model.addAttribute("alerta", false);
        model.addAttribute("generos", generos);
        model.addAttribute("formatos", formatos);
        model.addAttribute("directores", directores);
        return "peliculas/formularioPelicula";

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

    @RequestMapping(value = "/detalle/{id}", method = RequestMethod.GET)
    public String mostrarDetallePelicula(Model model, @PathVariable int id) {

        Pelicula peli = peliculaService.buscarPorId(id);

        model.addAttribute("pelicula", peli);

        return "peliculas/detallePelicula";

    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminarPelicula(@PathVariable int id, Model model) throws IOException {

        Pelicula pelicula = peliculaService.buscarPorId(id);

        cloudinaryService.delete(pelicula.getPortada_id());
        peliculaService.eliminarPeli(id);

        return "redirect:/admin/peliculas/index";

    }

    // Filtro
    @RequestMapping(value = "/filtrar", method = RequestMethod.GET)
    public String filtrar(@RequestParam(required = false) Boolean destacada,
            @RequestParam(required = false) Formato formato, @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String genero, Model model) {

        Specification<Pelicula> spec = construirSpecification(destacada, formato, titulo, genero);
        List<Pelicula> peliculas = peliculaService.filtrar(spec);

        List<String> generos = crearListaGeneros();
        List<String> formatos = crearListaFormatos();

        model.addAttribute("peliculas", peliculas);
        model.addAttribute("generos", generos);
        model.addAttribute("formatos", formatos);

        return "peliculas/listadoPeliculas";

    }

    // MÉTODOS POST
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(@RequestParam("codigoBarras") long codigoBarras, @RequestParam("titulo") String titulo,
            @RequestParam("director1") Integer dire1, @RequestParam(name = "director2", required = false) Integer dire2,
            @RequestParam("precio") BigDecimal precio,
            @RequestParam("portada") MultipartFile portada, @RequestParam("destacada") boolean destacada,
            @RequestParam("unidades") Integer unidades,
            @RequestParam("genero") String genero, @RequestParam("formato") Formato formato,
            @RequestParam("sinopsis") String sinopsis, Model model) throws IOException {

        List<Director> directores = new ArrayList<Director>();

        if (dire1 != null) {
            directores.add(directorService.buscarPorId(dire1));
        }

        if (dire2 != null) {
            directores.add(directorService.buscarPorId(dire2));
        }

        Map result = cloudinaryService.upload(portada);
        String portadaCloudinary = (String) result.get("url");
        String portada_id = (String) result.get("public_id");

        Pelicula pelicula = new Pelicula(codigoBarras, titulo, precio, portadaCloudinary, portada_id, destacada,
                unidades, genero, formato, sinopsis, directores);
        peliculaService.guardarPeli(pelicula);

        return "redirect:/admin/peliculas/index";

    }

    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    public String actualizar(@RequestParam("id") int id, @RequestParam("codigoBarras") long codigoBarras,
            @RequestParam("titulo") String titulo,
            @RequestParam("director1") Integer dire1, @RequestParam(name = "director2", required = false) Integer dire2,
            @RequestParam("precio") BigDecimal precio,
            @RequestParam(name = "portada", required = false) MultipartFile portada,
            @RequestParam("destacada") boolean destacada, @RequestParam("unidades") Integer unidades,
            @RequestParam("genero") String genero, @RequestParam("formato") Formato formato,
            @RequestParam("sinopsis") String sinopsis, Model model) throws IOException {

        Pelicula peliculaBBDD = peliculaService.buscarPorId(id);

        List<Director> directores = new ArrayList<Director>();

        if (dire1 != null) {
            directores.add(directorService.buscarPorId(dire1));
        }

        if (dire2 != null) {
            directores.add(directorService.buscarPorId(dire2));
        }

        peliculaBBDD.setCodigoBarras(codigoBarras);
        peliculaBBDD.setTitulo(titulo);
        peliculaBBDD.setDirectores(directores);
        peliculaBBDD.setPrecio(precio);

        if (!portada.isEmpty()) {

            System.out.println(peliculaBBDD.getPortada_id());

            if (peliculaBBDD.getPortada_id() != null) {
                cloudinaryService.delete(peliculaBBDD.getPortada_id());
            }

            Map result = cloudinaryService.upload(portada);

            String portadaCloudinary = (String) result.get("url");
            String portada_id = (String) result.get("public_id");

            peliculaBBDD.setPortada(portadaCloudinary);
            peliculaBBDD.setPortada_id(portada_id);

        }

        peliculaBBDD.setDestacada(destacada);
        peliculaBBDD.setUnidades(unidades);
        peliculaBBDD.setGenero(genero);
        peliculaBBDD.setFormato(formato);
        peliculaBBDD.setSinopsis(sinopsis);

        peliculaService.guardarPeli(peliculaBBDD);

        return "redirect:/admin/peliculas/index";

    }

    private List<String> crearListaGeneros() {

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

    private List<String> crearListaFormatos() {

        ArrayList<String> formatos = new ArrayList<String>();

        formatos.add("DVD");
        formatos.add("Bluray");
        formatos.add("UHD4K");

        return formatos;

    }

    private Specification<Pelicula> construirSpecification(Boolean destacada, Formato formato, String titulo,
            String genero) {

        // Seteamos el objeto spec con la cláusula where a null para que de forma
        // predeterminada haga un findAll normal
        Specification<Pelicula> spec = Specification.where(null);

        if (destacada != null) {
            spec = spec.and(PeliculaSpecification.destacada(destacada));
        }

        if (formato != null) {
            spec = spec.and(PeliculaSpecification.formato(formato));
        }

        if (titulo != "") {
            spec = spec.and(PeliculaSpecification.titulo(titulo));
        }

        if (genero != "") {
            spec = spec.and(PeliculaSpecification.genero(genero));
        }

        return spec;
    }

}

package com.diego.findeciclo.specification;

import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;

import com.diego.findeciclo.model.Formato;
import com.diego.findeciclo.model.Pelicula;

public class PeliculaSpecification {

	public static Specification<Pelicula> codigoBarras(Long codigoBarras) {
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get("codigoBarras")), codigoBarras);
        };
    }

    public static Specification<Pelicula> destacada(boolean destacada) {
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get("destacada")), destacada);
        };
    }

    public static Specification<Pelicula> formato(Formato formato) {
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get("formato")), formato);
        };
    }

    public static Specification<Pelicula> titulo(String titulo) {
        return (root, query, builder) -> {
            return builder.like(builder.lower(root.get("titulo")), "%"+titulo.toLowerCase()+"%");
        };
    }

    public static Specification<Pelicula> genero(String genero) {
        return (root, query, builder) -> {
            return builder.like(builder.lower(root.get("genero")), "%"+genero.toLowerCase()+"%");
        };
    }
    
    public static Specification<Pelicula> precioMinMax(BigDecimal min, BigDecimal max) {
        return (root, query, builder) -> {
            return builder.between(root.get("precio"), min, max);
        };
    }


    public static Specification<Pelicula> precioMin(BigDecimal min) {
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get("precio"), min);
        };
    }

    public static Specification<Pelicula> precioMax(BigDecimal max) {
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get("precio"), max);
        };
    }
	
}

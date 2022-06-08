package com.diego.findeciclo.specification;

import org.springframework.data.jpa.domain.Specification;
import com.diego.findeciclo.model.Director;

public class DirectorSpecification {

    public static Specification<Director> nombre(String nombre) {
        return (root, query, builder) -> {
            return builder.like(builder.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%");
        };
    }

    public static Specification<Director> apellido(String apellido) {
        return (root, query, builder) -> {
            return builder.like(builder.lower(root.get("apellido")), "%" + apellido.toLowerCase() + "%");
        };
    }

    public static Specification<Director> pais(String pais) {
        return (root, query, builder) -> {
            return builder.like(builder.lower(root.get("pais")), "%" + pais.toLowerCase() + "%");
        };
    }

}

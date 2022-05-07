package com.diego.findeciclo.specification;

import org.springframework.data.jpa.domain.Specification;
import com.diego.findeciclo.model.Pedido;

public class PedidoSpecification {

	public static Specification<Pedido> nombre(String nombre) {
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get("pedidosUsuario").get("nombre")), nombre);
        };
    }

    public static Specification<Pedido> apellido(String apellido) {
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get("pedidosUsuario").get("apellido")), apellido);
        };
    }

    public static Specification<Pedido> entregado(boolean entregado) {
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get("entregado")), entregado);
        };
    }

}

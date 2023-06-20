package com.marivel.marivelshop.entidades.carrito;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ItemCarritoKey implements Serializable {

    @Column(name = "id_carrito")
    private int idCarrito;

    @Column(name = "id_producto")
    private int idProducto;
}

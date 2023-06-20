package com.marivel.marivelshop.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class DetalleVentaKey implements Serializable {

    @Column(name = "id_venta")
    private int idVenta;

    @Column(name = "id_producto")
    private int idProducto;
}

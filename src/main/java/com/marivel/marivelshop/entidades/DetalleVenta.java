package com.marivel.marivelshop.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @EmbeddedId
    @EqualsAndHashCode.Include
    @ToString.Include
    private DetalleVentaKey id = new DetalleVentaKey();

    @EqualsAndHashCode.Include
    @ToString.Include
    private int cantidad;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @MapsId("idVenta")
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public double CalcularMonto() {
        return producto.getPrecio() * cantidad;
    }
}

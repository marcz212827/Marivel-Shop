package com.marivel.marivelshop.entidades.carrito;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.marivel.marivelshop.entidades.Producto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_carrito")
public class ItemCarrito {

    @EmbeddedId
    @EqualsAndHashCode.Include
    @ToString.Include
    private ItemCarritoKey id = new ItemCarritoKey();

    @EqualsAndHashCode.Include
    @ToString.Include
    private int cantidad;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @MapsId("idCarrito")
    @JoinColumn(name = "id_carrito", nullable = false)
    private CarritoCompras carrito;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    public boolean EsMismoProducto(ItemCarrito item) {
        return getProducto().getId() == item.getProducto().getId();
    }

    public void AgregarCantidad(ItemCarrito item) {
        cantidad = cantidad + item.cantidad;
    }

    public double CalcularMonto() {
        return producto.getPrecio() * cantidad;
    }
}

package com.marivel.marivelshop.servicios;

import com.marivel.marivelshop.entidades.carrito.ItemCarrito;
import com.marivel.marivelshop.entidades.carrito.ItemCarritoKey;

public interface IItemCarritoService {

    public void ActualizarCantidad(ItemCarritoKey itemCarritoKey, int cantidad);

    public void EliminarPorId(ItemCarritoKey itemCarritoKey);

    public void Guardar(ItemCarrito itemCarrito);
}

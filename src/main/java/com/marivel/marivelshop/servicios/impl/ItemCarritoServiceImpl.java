package com.marivel.marivelshop.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marivel.marivelshop.entidades.carrito.ItemCarrito;
import com.marivel.marivelshop.entidades.carrito.ItemCarritoKey;
import com.marivel.marivelshop.repositorios.IItemCarrito;
import com.marivel.marivelshop.servicios.IItemCarritoService;

@Service
public class ItemCarritoServiceImpl implements IItemCarritoService {

    @Autowired
    IItemCarrito data;

    /** No ejecuta la operacion si la cantidad es menor o igual a 0. */
    @Override
    public void ActualizarCantidad(ItemCarritoKey itemCarritoKey, int cantidad) {
        if (cantidad <= 0) {
            return;
        }
        Optional<ItemCarrito> obj = data.findById(itemCarritoKey);
        if (obj.isPresent()) {
            ItemCarrito itemCarrito = obj.orElseThrow();
            itemCarrito.setCantidad(cantidad);
            Guardar(itemCarrito);
        }
    }

    @Override
    public void EliminarPorId(ItemCarritoKey itemCarritoKey) {
        data.deleteById(itemCarritoKey);
    }

    @Override
    public void Guardar(ItemCarrito itemCarrito) {
        data.save(itemCarrito);
    }
}

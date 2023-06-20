package com.marivel.marivelshop.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marivel.marivelshop.entidades.carrito.ItemCarrito;
import com.marivel.marivelshop.entidades.carrito.ItemCarritoKey;

@Repository
public interface IItemCarrito extends CrudRepository<ItemCarrito, ItemCarritoKey> {

}

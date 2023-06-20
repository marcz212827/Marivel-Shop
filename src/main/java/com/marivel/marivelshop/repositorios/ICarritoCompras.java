package com.marivel.marivelshop.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marivel.marivelshop.entidades.carrito.CarritoCompras;

@Repository
public interface ICarritoCompras extends CrudRepository<CarritoCompras, Integer> {

    public Optional<CarritoCompras> findByTokenSession(String tokenSession);
}

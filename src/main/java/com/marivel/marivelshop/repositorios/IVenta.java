package com.marivel.marivelshop.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marivel.marivelshop.entidades.Venta;

@Repository
public interface IVenta extends CrudRepository<Venta, Integer> {

    public List<Venta> findByClienteId(int idCliente);
}

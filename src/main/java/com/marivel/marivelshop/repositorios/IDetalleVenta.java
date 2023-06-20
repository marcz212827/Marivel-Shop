package com.marivel.marivelshop.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.marivel.marivelshop.entidades.DetalleVenta;
import com.marivel.marivelshop.entidades.DetalleVentaKey;

public interface IDetalleVenta extends CrudRepository<DetalleVenta, DetalleVentaKey> {

}

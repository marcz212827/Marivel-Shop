package com.marivel.marivelshop.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marivel.marivelshop.entidades.Cliente;

@Repository
public interface ICliente extends CrudRepository<Cliente, Integer> {

}

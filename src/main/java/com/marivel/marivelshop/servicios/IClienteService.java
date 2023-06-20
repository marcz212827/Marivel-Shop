package com.marivel.marivelshop.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.marivel.marivelshop.entidades.Cliente;

public interface IClienteService {

    public List<Cliente> Listar();

    public Optional<Cliente> BuscarClienteActual(Authentication authentication);

    public void Guardar(Cliente cliente);
}

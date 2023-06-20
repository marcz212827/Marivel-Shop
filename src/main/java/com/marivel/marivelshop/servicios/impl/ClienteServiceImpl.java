package com.marivel.marivelshop.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.marivel.marivelshop.entidades.Cliente;
import com.marivel.marivelshop.entidades.login.Usuario;
import com.marivel.marivelshop.repositorios.ICliente;
import com.marivel.marivelshop.servicios.IClienteService;
import com.marivel.marivelshop.servicios.IUsuarioService;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    ICliente data;

    @Autowired
    IUsuarioService usuarioService;

    public List<Cliente> Listar() {
        return (List<Cliente>) data.findAll();
    }

    @Override
    public Optional<Cliente> BuscarClienteActual(Authentication authentication) {
        return usuarioService.BuscarUsuarioActual(authentication)
                .map(Usuario::getCliente);
    }

    @Override
    public void Guardar(Cliente cliente) {
        data.save(cliente);
    }
}

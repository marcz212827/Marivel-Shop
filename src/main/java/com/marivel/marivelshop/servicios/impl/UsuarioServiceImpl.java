package com.marivel.marivelshop.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.marivel.marivelshop.entidades.login.Usuario;
import com.marivel.marivelshop.repositorios.IUsuario;
import com.marivel.marivelshop.servicios.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuario data;

    @Override
    public Optional<Usuario> BuscarPorUsername(String username) {
        return data.findByUsername(username);
    }

    public Optional<Usuario> BuscarUsuarioActual(Authentication authentication) {
        return BuscarPorUsername(authentication.getName());
    }

    @Override
    public void Guardar(Usuario u) {
        Optional<Usuario> usuario = BuscarPorUsername(u.getUsername());
        if (usuario.isEmpty()) {
            data.save(u);
        }
    }
}

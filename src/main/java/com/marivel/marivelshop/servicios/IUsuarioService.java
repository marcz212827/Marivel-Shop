package com.marivel.marivelshop.servicios;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.marivel.marivelshop.entidades.login.Usuario;

public interface IUsuarioService {

    public Optional<Usuario> BuscarPorUsername(String username);

    public Optional<Usuario> BuscarUsuarioActual(Authentication authentication);

    public void Guardar(Usuario u);
}

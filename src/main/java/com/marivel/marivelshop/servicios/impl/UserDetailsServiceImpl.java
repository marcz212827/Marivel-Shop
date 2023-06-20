package com.marivel.marivelshop.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marivel.marivelshop.entidades.login.Rol;
import com.marivel.marivelshop.entidades.login.Usuario;
import com.marivel.marivelshop.servicios.IUsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = service.BuscarPorUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
        List<GrantedAuthority> autoridades = new ArrayList<>();
        for (Rol rol : usuario.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + rol.name());
            autoridades.add(grantedAuthority);
        }
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(),
                true, true, true, autoridades);
    }
}

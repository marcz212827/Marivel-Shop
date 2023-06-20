package com.marivel.marivelshop.repositorios;

import com.marivel.marivelshop.entidades.login.Usuario;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Long> {

    // El nombre del metodo debe ser "findByUsername" para que funcione
    public Optional<Usuario> findByUsername(String username);
}

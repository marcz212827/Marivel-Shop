package com.marivel.marivelshop.repositorios;

import com.marivel.marivelshop.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducto extends CrudRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM producto "
            + "WHERE nombre LIKE %?1% "
            + "OR descripcion LIKE %?1% "
            + "OR precio LIKE %?1% ", nativeQuery = true)
    List<Producto> buscarPorTodo(String dato);

    @Query(value = "SELECT * FROM producto ORDER BY id", nativeQuery = true)
    List<Producto> OrdenAscendente();

    @Query(value = "SELECT * FROM producto ORDER BY id DESC", nativeQuery = true)
    List<Producto> OrdenDescendente();
}

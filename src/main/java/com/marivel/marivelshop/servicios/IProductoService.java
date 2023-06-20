package com.marivel.marivelshop.servicios;

import com.marivel.marivelshop.entidades.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    public List<Producto> Listar();

    public Optional<Producto> ConsultarId(int id);

    public void Guardar(Producto p);

    public void Eliminar(int id);

    public List<Producto> Buscar(String dato);

    public List<Producto> OrdenAscendente();

    public List<Producto> OrdenDescendente();
}

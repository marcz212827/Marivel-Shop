package com.marivel.marivelshop.servicios.impl;

import com.marivel.marivelshop.entidades.Producto;
import com.marivel.marivelshop.repositorios.IProducto;
import com.marivel.marivelshop.servicios.IProductoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProducto data;

    @Override
    public List<Producto> Listar() {
        return (List<Producto>) data.findAll();
    }

    @Override
    public Optional<Producto> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Producto p) {
        if (p.getImagen() == null) {
            Optional<Producto> producto = data.findById(p.getId());
            if (producto.isPresent()) {
                p.setImagen(producto.orElseThrow().getImagen());
            }
        }
        data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Producto> Buscar(String dato) {
        return data.buscarPorTodo(dato);
    }

    @Override
    public List<Producto> OrdenAscendente() {
        return (List<Producto>) data.OrdenAscendente();
    }

    @Override
    public List<Producto> OrdenDescendente() {
        return (List<Producto>) data.OrdenDescendente();
    }
}

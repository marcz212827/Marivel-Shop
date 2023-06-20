package com.marivel.marivelshop.servicios.impl;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marivel.marivelshop.entidades.Producto;
import com.marivel.marivelshop.entidades.carrito.CarritoCompras;
import com.marivel.marivelshop.entidades.carrito.ItemCarrito;
import com.marivel.marivelshop.repositorios.ICarritoCompras;
import com.marivel.marivelshop.servicios.ICarritoComprasService;
import com.marivel.marivelshop.servicios.IItemCarritoService;
import com.marivel.marivelshop.servicios.IProductoService;

@Service
public class CarritoComprasServiceImpl implements ICarritoComprasService {

    @Autowired
    ICarritoCompras data;

    @Autowired
    IProductoService productoService;

    @Autowired
    IItemCarritoService itemCarritoService;

    // @Override
    // public CarritoCompras BuscarOCrearPorToken(String tokenSession) {
    // Optional<CarritoCompras> carrito = data.findByTokenSession(tokenSession);
    // if (carrito.isPresent()) {
    // return carrito.orElseThrow();
    // } else {
    // CarritoCompras carritoCompras = new CarritoCompras();
    // carritoCompras.setTokenSession(tokenSession);
    // carritoCompras.setFecha(new Date());
    // // Guardar carrito
    // Guardar(carritoCompras);

    // return carritoCompras;
    // }
    // }

    @Override
    public Optional<CarritoCompras> BuscarPorToken(String tokenSession) {
        return data.findByTokenSession(tokenSession);
    }

    @Override
    public Optional<CarritoCompras> ObtenerCarrito(HttpSession session) {
        String tokenSession = (String) session.getAttribute("tokenSession");
        if (tokenSession != null) {
            return BuscarPorToken(tokenSession);
        }
        return Optional.empty();
    }

    @Override
    public void AgregarACarrito(HttpSession session, int idProducto, int cantidad) {
        if (cantidad <= 0) {
            return;
        }
        Optional<Producto> producto = productoService.ConsultarId(idProducto);
        if (producto.isEmpty()) {
            return;
        }
        CarritoCompras carritoCompras = this.ObtenerCarrito(session).orElse(null);
        if (carritoCompras == null) {
            String tokenSession = session.getId();
            session.setAttribute("tokenSession", tokenSession);

            carritoCompras = new CarritoCompras();
            carritoCompras.setTokenSession(tokenSession);
            carritoCompras.setFecha(new Date());
            // Guardar carrito
            this.Guardar(carritoCompras);
        }
        ItemCarrito itemCarrito = new ItemCarrito();
        itemCarrito.setCantidad(cantidad);
        itemCarrito.setProducto(producto.orElseThrow());

        Optional<ItemCarrito> mismoProducto = carritoCompras.BuscarItemCarritoConMismoProducto(itemCarrito);
        if (mismoProducto.isPresent()) {
            ItemCarrito antiguoItemCarrito = mismoProducto.orElseThrow();

            System.out.println("Actualizar carrito con " + itemCarrito);
            System.out.println("antiguoItemCarrito=" + antiguoItemCarrito);

            antiguoItemCarrito.AgregarCantidad(itemCarrito);

            System.out.println("Resultado " + antiguoItemCarrito);
            // Actualizar item carrito
            // Tecnicamente se debe propagar
            itemCarritoService.Guardar(antiguoItemCarrito);
        } else {
            System.out.println("Carrito: " + carritoCompras);
            itemCarrito.setCarrito(carritoCompras);
            System.out.println("Añadir item carrito" + itemCarrito);
            // Guardar item carrito
            // Tecnicamente se debe propagar
            itemCarritoService.Guardar(itemCarrito);
        }
    }

    public void EliminarCarrito(CarritoCompras carritoCompras) {
        data.delete(carritoCompras);
    }

    @Override
    public void Guardar(CarritoCompras carritoCompras) {
        data.save(carritoCompras);
    }
}

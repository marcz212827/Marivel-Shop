package com.marivel.marivelshop.servicios.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.marivel.marivelshop.entidades.Cliente;
import com.marivel.marivelshop.entidades.DetalleVenta;
import com.marivel.marivelshop.entidades.Venta;
import com.marivel.marivelshop.entidades.carrito.CarritoCompras;
import com.marivel.marivelshop.entidades.carrito.ItemCarrito;
import com.marivel.marivelshop.repositorios.IVenta;
import com.marivel.marivelshop.servicios.ICarritoComprasService;
import com.marivel.marivelshop.servicios.IClienteService;
import com.marivel.marivelshop.servicios.IDetalleVentaService;
import com.marivel.marivelshop.servicios.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    private IVenta data;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ICarritoComprasService carritoComprasService;

    @Autowired
    private IDetalleVentaService detalleVentaService;

    @Override
    public List<Venta> Listar() {
        return (List<Venta>) data.findAll();
    }

    @Override
    public List<Venta> ListarPorCliente(Authentication authentication) {
        Cliente cliente = clienteService.BuscarClienteActual(authentication).orElse(null);
        if (cliente == null) {
            return List.of();
        } else {
            return data.findByClienteId(cliente.getId());
        }
    }

    @Override
    public Optional<Venta> ConsultarIdPorCliente(int idVenta, Authentication authentication) {
        Cliente cliente = clienteService.BuscarClienteActual(authentication).orElse(null);
        if (cliente == null) {
            // TODO: Tal vez lanzar excepcion
            return Optional.empty();
        }
        return data.findById(idVenta)
                .filter(v -> v.getCliente().getId() == cliente.getId());
    }

    @Override
    public void CrearDesdeCarrito(Authentication authentication, HttpSession session) {
        Cliente cliente = clienteService.BuscarClienteActual(authentication).orElse(null);
        if (cliente == null) {
            return;
        }
        CarritoCompras carritoCompras = carritoComprasService.ObtenerCarrito(session).orElse(null);
        if (carritoCompras == null || carritoCompras.isEmpty()) {
            return;
        }
        Venta venta = new Venta();
        venta.setFecha(new Date());
        venta.setCliente(cliente);
        this.Guardar(venta);

        for (ItemCarrito itemCarrito : carritoCompras.getItemsCarrito()) {
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setVenta(venta);
            detalleVenta.setProducto(itemCarrito.getProducto());
            detalleVenta.setCantidad(itemCarrito.getCantidad());

            detalleVentaService.Guardar(detalleVenta);
        }
        carritoComprasService.EliminarCarrito(carritoCompras);
    }

    @Override
    public void Guardar(Venta venta) {
        data.save(venta);
    }
}

package com.marivel.marivelshop.servicios;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;

import com.marivel.marivelshop.entidades.Venta;

public interface IVentaService {

    public List<Venta> Listar();

    public List<Venta> ListarPorCliente(Authentication authentication);

    public Optional<Venta> ConsultarIdPorCliente(int idVenta, Authentication authentication);

    public void CrearDesdeCarrito(Authentication authentication, HttpSession session);

    public void Guardar(Venta venta);
}

package com.marivel.marivelshop.servicios;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.marivel.marivelshop.entidades.carrito.CarritoCompras;

public interface ICarritoComprasService {

    // public CarritoCompras BuscarOCrearPorToken(String tokenSession);

    public Optional<CarritoCompras> BuscarPorToken(String tokenSession);

    public Optional<CarritoCompras> ObtenerCarrito(HttpSession session);

    public void AgregarACarrito(HttpSession session, int idProducto, int cantidad);

    public void EliminarCarrito(CarritoCompras carritoCompras);

    public void Guardar(CarritoCompras carritoCompras);
}

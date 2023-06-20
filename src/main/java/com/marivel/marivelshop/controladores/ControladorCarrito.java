package com.marivel.marivelshop.controladores;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marivel.marivelshop.entidades.carrito.CarritoCompras;
import com.marivel.marivelshop.entidades.carrito.ItemCarritoKey;
import com.marivel.marivelshop.servicios.ICarritoComprasService;
import com.marivel.marivelshop.servicios.IItemCarritoService;
import com.marivel.marivelshop.servicios.IVentaService;

@Controller
@RequestMapping("/carrito")
public class ControladorCarrito {

    String carpeta = "carrito/";

    @Autowired
    ICarritoComprasService carritoComprasService;

    @Autowired
    IItemCarritoService itemCarritoService;

    @Autowired
    IVentaService ventaService;

    private String redirigirAComprar() {
        return "redirect:/index";
    }

    private String redirigirACarrito() {
        return "redirect:/carrito/mostrar";
    }

    private String redirigirAVentas() {
        return "redirect:/venta/listar";
    }

    @GetMapping("/mostrar")
    public String Mostrar(HttpSession session, Model model) {
        Optional<CarritoCompras> carritoCompras = carritoComprasService.ObtenerCarrito(session);
        if (carritoCompras.isPresent()) {
            model.addAttribute("items", carritoCompras.orElseThrow().getItemsCarrito());
            model.addAttribute("monto", carritoCompras.orElseThrow().CalcularMonto());
        }
        return carpeta + "mostrar";
    }

    @PostMapping("/agregar")
    public String Agregar(HttpSession session, @RequestParam("id_producto") int idProducto,
            @RequestParam("cantidad") int cantidad, Model model) {
        carritoComprasService.AgregarACarrito(session, idProducto, cantidad);
        return redirigirAComprar();
    }

    @PostMapping("/actualizar")
    public String Actualizar(
            @RequestParam("id_carrito") int idCarrito,
            @RequestParam("id_producto") int idProducto,
            @RequestParam("cantidad") int cantidad,
            Model model) {
        ItemCarritoKey itemCarritoKey = new ItemCarritoKey();
        itemCarritoKey.setIdCarrito(idCarrito);
        itemCarritoKey.setIdProducto(idProducto);
        itemCarritoService.ActualizarCantidad(itemCarritoKey, cantidad);
        return redirigirACarrito();
    }

    @GetMapping("/eliminar/{carrito}/{producto}")
    public String Eliminar(@PathVariable("carrito") int idCarrito,
            @PathVariable("producto") int idProducto, Model model) {
        ItemCarritoKey itemCarritoKey = new ItemCarritoKey();
        itemCarritoKey.setIdCarrito(idCarrito);
        itemCarritoKey.setIdProducto(idProducto);
        itemCarritoService.EliminarPorId(itemCarritoKey);
        return redirigirACarrito();
    }

    @PostMapping("/comprar")
    public String Comprar(HttpSession session, Authentication authentication, Model model) {
        ventaService.CrearDesdeCarrito(authentication, session);
        return redirigirAVentas();
    }
}

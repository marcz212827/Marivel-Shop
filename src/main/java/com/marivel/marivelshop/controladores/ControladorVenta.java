package com.marivel.marivelshop.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marivel.marivelshop.entidades.Venta;
import com.marivel.marivelshop.servicios.IVentaService;

@Controller
@RequestMapping("/venta")
public class ControladorVenta {

    String carpeta = "venta/";

    @Autowired
    IVentaService ventaService;

    @GetMapping("/listar")
    public String Listar(Authentication authentication, Model model) {
        List<Venta> ventas = ventaService.ListarPorCliente(authentication);
        model.addAttribute("ventas", ventas);
        return carpeta + "listar";
    }

    @GetMapping("/detalle/{id}")
    public String Detalle(@PathVariable int id, Authentication authentication, Model model) {
        Venta venta = ventaService.ConsultarIdPorCliente(id, authentication).orElse(null);
        if (venta != null) {
            model.addAttribute("detalle", venta.getDetalleVenta());
            model.addAttribute("monto", venta.CalcularMonto());
        }
        return carpeta + "detalle";
    }
}

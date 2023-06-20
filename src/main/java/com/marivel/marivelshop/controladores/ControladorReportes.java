package com.marivel.marivelshop.controladores;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marivel.marivelshop.entidades.Cliente;
import com.marivel.marivelshop.entidades.DetalleVenta;
import com.marivel.marivelshop.entidades.Producto;
import com.marivel.marivelshop.entidades.Venta;
import com.marivel.marivelshop.servicios.IClienteService;
import com.marivel.marivelshop.servicios.IProductoService;
import com.marivel.marivelshop.servicios.IVentaService;

@Controller
@RequestMapping("/reportes")
public class ControladorReportes {

    String carpeta = "reportes/";

    @Autowired
    IVentaService ventaService;

    @Autowired
    IClienteService clienteService;

    @Autowired
    IProductoService productoService;

    @GetMapping("/")
    public String Mostrar(Authentication authentication, Model model) {
        // Ventas por cliente
        Map<String, Integer> ventasCliente = new TreeMap<>();
        List<Cliente> clientes = clienteService.Listar();
        for (Cliente c : clientes) {
            ventasCliente.put(c.getUsuario().getUsername(), c.getVentas().size());
        }
        model.addAttribute("ventasCliente", ventasCliente);

        // Total de Ventas por mes
        Map<Month, Double> totalVentasMes = new TreeMap<>();
        List<Venta> ventas = ventaService.Listar();
        for (Venta v : ventas) {
            LocalDateTime fecha = LocalDateTime.ofInstant(v.getFecha().toInstant(), ZoneId.systemDefault());
            Month mes = fecha.getMonth();
            double monto = v.CalcularMonto();
            mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            totalVentasMes.compute(mes, (m, contador) -> contador == null ? monto : contador + monto);
        }
        Map<String, Double> ventasMes = totalVentasMes.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getDisplayName(TextStyle.FULL, new Locale("es", "ES")),
                        Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
        model.addAttribute("ventasMes", ventasMes);

        // Cantidad de productos vendidos
        Map<String, Integer> productosVendidos = new TreeMap<>();
        List<Producto> productos = productoService.Listar();
        for (Producto p : productos) {
            int cantidad = p.getDetalleVenta()
                    .stream()
                    .mapToInt(DetalleVenta::getCantidad)
                    .sum();
            productosVendidos.put(p.getNombre(), cantidad);
        }
        model.addAttribute("productosVendidos", productosVendidos);

        return carpeta + "mostrar";
    }
    //@GetMapping("/buscar-cotizacion")
//public String buscarCotizacion(@RequestParam("ventaId") Long ventaId, Model model) {
    // Lógica para buscar los datos de cotización correspondientes a la ventaId
    
    // Agregar los datos encontrados al modelo para mostrarlos en la vista
    
    //return carpeta + "resultado-cotizacion";
}

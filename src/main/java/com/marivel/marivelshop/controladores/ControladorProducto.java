package com.marivel.marivelshop.controladores;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.marivel.marivelshop.entidades.Producto;
import com.marivel.marivelshop.servicios.IProductoService;
import com.marivel.marivelshop.utils.ImagenUtils;

@Controller
@RequestMapping("/producto")
public class ControladorProducto {

    String carpeta = "producto/";

    @Autowired
    private IProductoService service;

    @GetMapping("/listar")
    public String Listar(@RequestParam(required = false) Integer eliminar, Model model) {
        List<Producto> productos = service.Listar();
        model.addAttribute("productos", productos);
        model.addAttribute("eliminar", eliminar != null);
        return carpeta + "listar";
    }

    private String redirigirAListar() {
        return "redirect:/producto/listar";
    }

    @GetMapping("/nuevo")
    public String Nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("button_text", "Guardar");
        return carpeta + "nuevo";
    }

    @GetMapping("/actualizar/{id}")
    public String Actualizar(@PathVariable int id, Model model) {
        Optional<Producto> producto = service.ConsultarId(id);
        if (producto.isEmpty()) {
            return redirigirAListar();
        }
        model.addAttribute("producto", producto.orElseThrow());
        model.addAttribute("button_text", "Actualizar");
        return carpeta + "nuevo";
    }

    @PostMapping("/guardar")
    public String Guardar(@Valid Producto producto, @RequestParam("img") MultipartFile file, Model model) {
        System.out.println("MultipartFile: " + file);
        System.out.println("file.isEmpty(): " + file.isEmpty());
        if (!file.isEmpty()) {
            String archivo = null;
            try {
                archivo = ImagenUtils.subirImagen(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("archivo: " + archivo);
            System.out.println("producto antes: " + producto);
            producto.setImagen(archivo);
            System.out.println("producto despues: " + producto);
        }

        service.Guardar(producto);

        return redirigirAListar();
    }

    @GetMapping("/eliminar/{id}")
    public String Eliminar(@PathVariable int id, Model model) {
        service.Eliminar(id);

        // return Listar(model);
        // return redirigirAListar();
        return "redirect:/producto/listar?eliminar=1";
    }

    // @PostMapping("/actualizar")
    // public String Actualizar(@RequestParam("id") int id,
    // @RequestParam("nom") String nom,
    // @RequestParam("desc") String desc,
    // @RequestParam("prec") float prec,
    // Model model) {
    // Producto p = new Producto();
    // p.setId(id);
    // p.setNombre(nom);
    // p.setDescripcion(desc);
    // p.setPrecio(prec);
    //
    // service.Guardar(p);
    //
    // return Listar(model);
    // }

    // @GetMapping("/listar-test")
    // public String Mostrar2(Model model) {
    // List<Producto> productos = service.Listar();
    // int cantidadAtenciones = productos.size();
    // // double totalAtenciones = 0;
    // // for (Producto data : productos) {
    // // totalAtenciones = totalAtenciones + data.getPrecio();
    // // }
    // double totalAtenciones = productos.stream()
    // .mapToDouble(Producto::getPrecio)
    // .sum();
    // model.addAttribute("cantidadAtenciones", cantidadAtenciones);
    // model.addAttribute("totalAtenciones", totalAtenciones);

    // model.addAttribute("productos", productos);

    // return "test/lista";
    // }

    // @GetMapping("/listarAsc")
    // public String ListarAsc(Model model) {
    // List<Producto> productos = service.OrdenAscendente();
    // int cantidadAtenciones = productos.size();
    // double totalAtenciones = 0;
    // for (Producto data : productos) {
    // totalAtenciones = totalAtenciones + data.getPrecio();
    // }
    // model.addAttribute("cantidadAtenciones", cantidadAtenciones);
    // model.addAttribute("totalAtenciones", totalAtenciones);
    // model.addAttribute("productos", productos);

    // return "lista";
    // }

    // @GetMapping("/listarDesc")
    // public String ListarDesc(Model model) {
    // List<Producto> productos = service.OrdenDescendente();
    // int cantidadAtenciones = productos.size();
    // double totalAtenciones = 0;
    // for (Producto data : productos) {
    // totalAtenciones = totalAtenciones + data.getPrecio();
    // }
    // model.addAttribute("cantidadAtenciones", cantidadAtenciones);
    // model.addAttribute("totalAtenciones", totalAtenciones);
    // model.addAttribute("productos", productos);

    // return "lista";
    // }

    // @PostMapping("/buscar")
    // public String Buscar(@RequestParam("dato") String dato, Model model) {
    // List<Producto> productos = service.Buscar(dato);
    // model.addAttribute("productos", productos);
    // return "lista";
    // }
}

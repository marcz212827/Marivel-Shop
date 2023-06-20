package com.marivel.marivelshop.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marivel.marivelshop.entidades.Producto;
import com.marivel.marivelshop.servicios.IProductoService;

@Controller
@RequestMapping
public class ControladorPrincipal {

    String carpetaLogin = "login/";

    @Autowired
    private IProductoService service;

    @GetMapping({ "", "/index" })
    public String Inicio(Model model) {
        List<Producto> productos = service.Listar();
        model.addAttribute("productos", productos);
        return "index";
    }

    @GetMapping("/login")

    public String Login(Model model) {
        return carpetaLogin + "login";
    }

    // @GetMapping("/menu")
    // public String Menu(Model model) {
    // return carpetaLogin + "menu";
    // }

    // @GetMapping("/admin")
    // public String Admin(Model model) {
    // return carpetaLogin + "admin";
    // }

    // @GetMapping("/cliente")
    // public String Cliente(Model model) {
    // return carpetaLogin + "cliente";
    // }
}

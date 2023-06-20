package com.marivel.marivelshop.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marivel.marivelshop.entidades.Cliente;
import com.marivel.marivelshop.entidades.login.Rol;
import com.marivel.marivelshop.entidades.login.Usuario;
import com.marivel.marivelshop.servicios.IClienteService;

@Controller
@RequestMapping("/cliente")
public class ControladorCliente {

    @Autowired
    private IClienteService service;

    String carpeta = "login/";

    private String redirigirALogin() {
        return "redirect:/login";
    }

    @GetMapping("/nuevo")
    public String Nuevo(Model model) {
        return carpeta + "nuevo";
    }

    @PostMapping("/guardar")
    public String Guardar(
            @RequestParam("user") String user,
            @RequestParam("password") String password,
            Model model) {
        Usuario usuario = new Usuario();
        usuario.setUsername(user);
        usuario.setPassword(password);
        usuario.setEnabled(true);
        usuario.setRol(Rol.CLIENTE);

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);

        // Persiste usuario automaticamente
        service.Guardar(cliente);

        return redirigirALogin();
    }
}

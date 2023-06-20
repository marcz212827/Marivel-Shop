package com.marivel.marivelshop;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marivel.marivelshop.entidades.Cliente;
import com.marivel.marivelshop.entidades.DetalleVenta;
import com.marivel.marivelshop.entidades.Producto;
import com.marivel.marivelshop.entidades.Venta;
import com.marivel.marivelshop.entidades.login.Rol;
import com.marivel.marivelshop.entidades.login.Usuario;
import com.marivel.marivelshop.servicios.IClienteService;
import com.marivel.marivelshop.servicios.IDetalleVentaService;
import com.marivel.marivelshop.servicios.IProductoService;
import com.marivel.marivelshop.servicios.IUsuarioService;
import com.marivel.marivelshop.servicios.IVentaService;

@SpringBootApplication
public class MarivelshopApplication implements CommandLineRunner {

        @Autowired
        private IProductoService productoService;

        @Autowired
        private IUsuarioService usuarioService;

        @Autowired
        private IClienteService clienteService;

        @Autowired
        private IVentaService ventaService;

        @Autowired
        private IDetalleVentaService detalleVentaService;

        public static void main(String[] args) {
                SpringApplication.run(MarivelshopApplication.class, args);
        }

        @Override
        public void run(String... args) throws Exception {
                if (true) {
                        return;
                }
                // Productos
                // Producto producto;
                Producto tv = new Producto();
                tv.setNombre("TV");
                tv.setDescripcion("Television de 20 pulgadas");
                tv.setCosto(20);
                tv.setPrecio(30);
                tv.setCantidad(10);
                tv.setImagen("https://img.freepik.com/vector-gratis/diseno-television-realista_1053-473.jpg?w=360");
                productoService.Guardar(tv);

                Producto refri = new Producto();
                refri.setNombre("Refrigerador");
                refri.setDescripcion("Refrigerador anti frost");
                refri.setCosto(28);
                refri.setPrecio(35);
                refri.setCantidad(7);
                refri.setImagen(
                                "https://e39a9f00db6c5bc097f9-75bc5dce1d64f93372e7c97ed35869cb.ssl.cf1.rackcdn.com/42518148_8-_E_TlcXK.jpg");
                productoService.Guardar(refri);

                Producto cubrecama = new Producto();
                cubrecama.setNombre("Cubrecama");
                cubrecama.setDescripcion("Cubrecama de 2 plazas");
                cubrecama.setCosto(8);
                cubrecama.setPrecio(10);
                cubrecama.setCantidad(20);
                cubrecama.setImagen(
                                "https://oechsle.vteximg.com.br/arquivos/ids/7474730-800-800/1949322_1.jpg?v=637818416693470000");
                productoService.Guardar(cubrecama);

                Producto olla = new Producto();
                olla.setNombre("Olla arrocera");
                olla.setDescripcion("Olla arrocera de 2L");
                olla.setCosto(6);
                olla.setPrecio(15);
                olla.setCantidad(9);
                olla.setImagen(
                                "https://lucero.com.pe/wp-content/uploads/2020/07/OSTER-OLLA-ARROCERA-CKSTRC1700W053-1.8L_1-600x514.jpg");
                productoService.Guardar(olla);

                // Usuarios
                Usuario usuario;

                usuario = new Usuario();
                usuario.setUsername("admin");
                usuario.setPassword("admin");
                usuario.setEnabled(true);
                usuario.setRol(Rol.ADMIN);
                usuarioService.Guardar(usuario);

                // Clientes
                usuario = new Usuario();
                usuario.setUsername("cliente");
                usuario.setPassword("cliente");
                usuario.setEnabled(true);
                usuario.setRol(Rol.CLIENTE);
                Cliente cliente1 = new Cliente();
                cliente1.setUsuario(usuario);
                clienteService.Guardar(cliente1);

                usuario = new Usuario();
                usuario.setUsername("cliente2");
                usuario.setPassword("cliente");
                usuario.setEnabled(true);
                usuario.setRol(Rol.CLIENTE);
                Cliente cliente2 = new Cliente();
                cliente2.setUsuario(usuario);
                clienteService.Guardar(cliente2);

                // Ventas
                Venta venta;
                Calendar calendar;
                DetalleVenta detalleVenta;

                // Ventas de cliente 1
                venta = new Venta();
                calendar = Calendar.getInstance();
                calendar.set(2022, 11, 5);
                venta.setFecha(calendar.getTime());
                venta.setCliente(cliente1);
                // TODO: Tal vez persistir DetalleVenta junto a Ventas
                ventaService.Guardar(venta);

                detalleVenta = new DetalleVenta();
                detalleVenta.setVenta(venta);
                detalleVenta.setProducto(olla);
                detalleVenta.setCantidad(2);
                detalleVentaService.Guardar(detalleVenta);

                detalleVenta = new DetalleVenta();
                detalleVenta.setVenta(venta);
                detalleVenta.setProducto(tv);
                detalleVenta.setCantidad(1);
                detalleVentaService.Guardar(detalleVenta);

                //
                venta = new Venta();
                calendar = Calendar.getInstance();
                calendar.set(2022, 5, 20);
                venta.setFecha(calendar.getTime());
                venta.setCliente(cliente1);
                ventaService.Guardar(venta);

                detalleVenta = new DetalleVenta();
                detalleVenta.setVenta(venta);
                detalleVenta.setProducto(refri);
                detalleVenta.setCantidad(2);
                detalleVentaService.Guardar(detalleVenta);

                detalleVenta = new DetalleVenta();
                detalleVenta.setVenta(venta);
                detalleVenta.setProducto(tv);
                detalleVenta.setCantidad(2);
                detalleVentaService.Guardar(detalleVenta);

                // Ventas de cliente 2
                venta = new Venta();
                calendar = Calendar.getInstance();
                calendar.set(2022, 12, 15);
                venta.setFecha(calendar.getTime());
                venta.setCliente(cliente2);
                ventaService.Guardar(venta);

                detalleVenta = new DetalleVenta();
                detalleVenta.setVenta(venta);
                detalleVenta.setProducto(cubrecama);
                detalleVenta.setCantidad(7);
                detalleVentaService.Guardar(detalleVenta);

                detalleVenta = new DetalleVenta();
                detalleVenta.setVenta(venta);
                detalleVenta.setProducto(olla);
                detalleVenta.setCantidad(1);
                detalleVentaService.Guardar(detalleVenta);
        }
}

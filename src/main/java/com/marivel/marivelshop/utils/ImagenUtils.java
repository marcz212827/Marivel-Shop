package com.marivel.marivelshop.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class ImagenUtils {

    public static Path directorio_imagen = Paths.get(System.getProperty("user.dir"),
            "src", "main", "resources", "static", "subidas");

    private static void crearDirectorio() throws IOException {
        if (!Files.exists(directorio_imagen)) {
            Files.createDirectories(directorio_imagen);
        }
    }

    public static String subirImagen(MultipartFile file) throws IOException {
        crearDirectorio();
        String archivo = file.getOriginalFilename();
        Path ruta = directorio_imagen.resolve(archivo);
        // TODO: Verificar si un archivo con el mismo nombre existe
        // TODO: Si es así, cambiar nombre
        Files.write(ruta, file.getBytes());
        return '/' + Paths.get("subidas", archivo).toString();
    }

    public static void main(String[] args) {
        System.out.println(directorio_imagen);
    }
}
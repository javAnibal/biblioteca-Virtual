package com.example.myapp.main;

import com.example.myapp.enums.TipoSuscripcion;
import com.example.myapp.models.Biblioteca;
import com.example.myapp.models.Libro;
import com.example.myapp.models.Usuario;
import com.example.myapp.utils.BibliotecaException;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        Biblioteca BibliotecaCentral = new Biblioteca();

        // Crear objetos Libro
        Libro libro1 = new Libro("Cien Años de Soledad", "Gabriel García Márquez", "978-8437604947", true);
        Libro libro2 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "978-0060934347", true);
        Libro libro3 = new Libro("El Principito", "Antoine de Saint-Exupéry", "978-0156012195", true);
        Libro libro4 = new Libro("1984", "George Orwell", "978-0451524935", true);
        Libro libro5 = new Libro("Crónica de una Muerte Anunciada", "Gabriel García Márquez", "978-1400034956", true);

        Collections.addAll(BibliotecaCentral.getListaLibros(), libro1, libro2, libro3, libro4, libro5);


        //Creando usuarios

        // Crear usuarios
        Usuario usuario1 = new Usuario("Juan Pérez", 25, 1, TipoSuscripcion.Basica);
        Usuario usuario2 = new Usuario("María López", 30, 2, TipoSuscripcion.Premium);
        Usuario usuario3 = new Usuario("Luis González", 28, 3, TipoSuscripcion.Vip);
        Usuario usuario4 = new Usuario("Ana Rodríguez", 22, 4, TipoSuscripcion.Basica);
        Usuario usuario5 = new Usuario("Carlos Martínez", 35, 5, TipoSuscripcion.Premium);

        Collections.addAll(BibliotecaCentral.getListaUsuarios(),usuario1, usuario2, usuario3, usuario4, usuario5);

        try {
            BibliotecaCentral.prestarLibro(usuario1,libro4);
            BibliotecaCentral.mostrarInfoPrestamos();
        } catch (BibliotecaException e) {
            System.out.println(e.getMessage());
        }



    }
}

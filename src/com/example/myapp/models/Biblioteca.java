package com.example.myapp.models;

import com.example.myapp.interfaces.Prestamo;
import com.example.myapp.utils.BibliotecaException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca implements Prestamo {

    private List<Usuario> listaUsuarios;
    private List<Libro> listaLibros;

    public Biblioteca() {
        this.listaUsuarios = new ArrayList<>();
        this.listaLibros = new ArrayList<>();
    }


    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }


    @Override
    public void registroUsuario(Usuario usuario) throws BibliotecaException {

        if (usuario.getNombre() == null) {
            throw new BibliotecaException("El campo usuario esta vacío!!");
        }
        for (Usuario usuario1 : listaUsuarios) {
            if (usuario1.getNombre().equalsIgnoreCase(usuario.getNombre())) {
                throw new BibliotecaException("El usuario ya ha sido registrado");
            }
        }
        listaUsuarios.add(usuario);
        System.out.println("Bienvenido usuario: " + usuario.getNumUsuario());

    }

    @Override
    public void prestarLibro(Usuario usuario, Libro libro) throws BibliotecaException {
       
        for (Libro libroEstado : listaLibros) {
            if (!libroEstado.isEstado() && libroEstado.getTitulo().equalsIgnoreCase(libro.getTitulo())) {
                throw new BibliotecaException("El libro \"" + libro.getTitulo() + "\" ya está prestado.");
            } else if (libroEstado.getTitulo().equalsIgnoreCase(libro.getTitulo())) {
                System.out.println("El libro \"" + libro.getTitulo() + "\" está disponible.");
            }
        }

        // Verificar si el usuario no ha alcanzado su límite de libros permitidos
        if (usuario.getListaLibros().size() >= usuario.getTipoSuscripcion().getLibrosMax()) {
            throw new BibliotecaException("El usuario ha alcanzado el límite de libros permitidos para su suscripción.");
        }

        // Registrar el préstamo
        usuario.getListaLibros().add(libro);
        libro.setEstado(false);

        // Mensaje de confirmación
        System.out.println("El libro \"" + libro.getTitulo() + "\" ha sido prestado al usuario \"" + usuario.getNombre() + "\".");
    }

    @Override
    public void devolverLibro(Usuario usuario, Libro libro) throws BibliotecaException {

        // Verificar si el usuario tiene el libro en préstamo
        if (!usuario.getListaLibros().contains(libro)) {
            throw new BibliotecaException("El usuario no tiene este libro en préstamo.");
        }
        libro.setEstado(true);
        usuario.getListaLibros().remove(libro);
        // Mensaje de confirmación
        System.out.println("El libro \"" + libro.getTitulo() + "\" ha sido devuelto con éxito por el usuario \"" + usuario.getNombre() + "\".");

    }

    @Override
    public void mostrarInfoPrestamos() {
        // Mostrar información de los usuarios y sus préstamos
        System.out.println("=== Información de Préstamos ===");
        for (Usuario usuario : listaUsuarios) {
            System.out.println("Usuario: " + usuario.getNombre() + " | Suscripción: " + usuario.getTipoSuscripcion());
            if (usuario.getListaLibros().isEmpty()) {
                System.out.println("  No tiene libros prestados.");
            } else {
                System.out.println("  Libros prestados:");
                for (Libro libro : usuario.getListaLibros()) {
                    System.out.println("    - " + libro.getTitulo() + " por " + libro.getAutor());
                }
            }
        }

        // Mostrar libros disponibles en la biblioteca
        System.out.println("\n=== Libros Disponibles en la Biblioteca ===");
        for (Libro libro : listaLibros) {
            if (libro.isEstado()) {
                System.out.println("  - " + libro.getTitulo() + " por " + libro.getAutor());
            }
        }
    }
}

package service;

import dao.ReporteDAO;
import entity.Libro;
import entity.Prestamo;

import java.util.Scanner;

public class ReporteService {
    ReporteDAO reporteDAO = new ReporteDAO();

    public void librosDisponible() {
        System.out.println("Mostrando listado de libros disponibles...");
        System.out.println("┌---┬---------------------┬------------------------------------┬---------------------------┬-----------------┬---------------------┐");
        System.out.println("│ID │ Título              │ Autor                              │ Año de Publicación        │ Género          │ Cantidad Disponible │");
        System.out.println("├---┼---------------------┼------------------------------------┼---------------------------┼-----------------┼---------------------┤");

        for (Libro l : reporteDAO.getLibrosDisponibles()) {
            System.out.printf("│%-3s│ %-20s│ %-35s│ %-26s│ %-16s│ %-20s│%n",
                    l.getId(),
                    l.getTitulo(),
                    l.getAutor(),
                    l.getAnioPublicacion(),
                    l.getGenero(),
                    l.getCantidadDisponible());
        }

        System.out.println("└---┴---------------------┴------------------------------------┴---------------------------┴-----------------┴---------------------┘");
    }

    public void LibrosPrestados() {
        System.out.println("Mostrando listado de libros prestados...");

        System.out.println("┌---------------------┬------------------------------------┬---------------------------┬-----------------┬---------------------┐");
        System.out.println("│ Título              │ Autor                              │ Año de Publicación        │ Género          │ Cantidad Disponible │");
        System.out.println("├---------------------┼------------------------------------┼---------------------------┼-----------------┼---------------------┤");

        for (Libro l : reporteDAO.getLibrosPrestados()) {
            System.out.printf("│%-20s │ %-35s│ %-26s│ %-16s│ %-20s│%n",
                    l.getTitulo(),
                    l.getAutor(),
                    l.getAnioPublicacion(),
                    l.getGenero(),
                    l.getCantidadDisponible());
        }

        System.out.println("└---------------------┴------------------------------------┴---------------------------┴-----------------┴---------------------┘");
    }

    public void prestamosPorUsuario(Scanner scanner) {
        System.out.print("Ingresar nombre: ");
        String usuario = scanner.nextLine();

        System.out.println("Mostrando préstamos de " + usuario + "...");
        System.out.println("┌------------------------┬------------------------┬----------------┐");
        System.out.println("│ Fecha de Prestamos     │ Fecha de Devolución    │ Estado         │");
        System.out.println("├------------------------┼------------------------┼----------------┤");

        for (Prestamo p : reporteDAO.getPrestamosPorUsuario(usuario)) {
            System.out.printf("│%-23s │ %-23s│ %-15s│%n",
                    p.getFechaPrestamo(),
                    p.getFechaDevolucion(),
                    p.getEstado());
        }

        System.out.println("└------------------------┴------------------------┴----------------┘");
    }
}

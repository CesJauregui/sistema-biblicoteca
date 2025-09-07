package service;

import dao.LibroDAO;
import entity.Libro;

import java.util.Scanner;

public class LibroService {
    LibroDAO libroDAO = new LibroDAO();

    public void newLibro(Scanner scanner) {
        System.out.print("Ingresar título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingresar Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ingresar Año de Publicación: ");
        String anioPublicacion = scanner.nextLine();
        System.out.print("Ingresar Género: ");
        String genero = scanner.nextLine();
        System.out.print("Ingresar Cantidad Disponible: ");
        Integer cantidadDisponible = scanner.nextInt();

        Libro libro = new Libro(titulo, autor, anioPublicacion, genero, cantidadDisponible);
        libroDAO.insertarLibro(libro);
        System.out.println("----------------------------------------");
    }

    public void getLibros() {
        System.out.println("Mostrando listado de libros...");
        System.out.println("┌---┬---------------------┬------------------------------------┬---------------------------┬-----------------┬---------------------┐");
        System.out.println("│ID │ Título              │ Autor                              │ Año de Publicación        │ Género          │ Cantidad Disponible │");
        System.out.println("├---┼---------------------┼------------------------------------┼---------------------------┼-----------------┼---------------------┤");

        for (Libro l : libroDAO.obtenerLibros()) {
            System.out.printf("│%-3s│ %-20s│ %-35s│ %-26s│ %-16s| %-20s|%n",
                    l.getId(),
                    l.getTitulo(),
                    l.getAutor(),
                    l.getAnioPublicacion(),
                    l.getGenero(),
                    l.getCantidadDisponible());
        }

        System.out.println("└---┴---------------------┴------------------------------------┴---------------------------┴-----------------┴---------------------┘");
        System.out.println("Fin lista de libros.");
    }

    public Libro getLibroPorID(Scanner scanner) {
        System.out.print("Ingrese el id de libro: ");
        Long id = scanner.nextLong();

        Libro l = libroDAO.obtenerLibroPorID(id);
        System.out.println("┌---┬---------------------┬------------------------------------┬---------------------------┬-----------------┬---------------------┐");
        System.out.println("│ID │ Título              │ Autor                              │ Año de Publicación        │ Género          │ Cantidad Disponible │");
        System.out.println("├---┼---------------------┼------------------------------------┼---------------------------┼-----------------┼---------------------┤");
        System.out.printf("│%-3s│ %-20s│ %-35s│ %-26s│ %-16s| %-20s|%n",
                l.getId(),
                l.getTitulo(),
                l.getAutor(),
                l.getAnioPublicacion(),
                l.getGenero(),
                l.getCantidadDisponible());
        System.out.println("└---┴---------------------┴------------------------------------┴---------------------------┴-----------------┴---------------------┘");
        return l;
    }

    public void editLibro(Scanner scanner) {
        Libro l = this.getLibroPorID(scanner);
        Long id = l.getId();

        System.out.println("¿Qué información quiere editar?");
        System.out.println("1 = Título, 2 = Autor, 3 = Año Pub., 4 = Género, 5 = Cant. Disponible");

        System.out.print("Opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            System.out.print("Ingresar Título: ");
            String titulo = scanner.nextLine();
            l.setTitulo(titulo);
            libroDAO.editarLibro(id, l);
            System.out.println("Se actualizó el título correctamente.");
            System.out.println("----------------------------------------");
        }
        if (option == 2) {
            System.out.print("Ingresar Autor: ");
            String autor = scanner.nextLine();
            l.setTitulo(autor);
            libroDAO.editarLibro(id, l);
            System.out.println("Se actualizó el autor correctamente.");
            System.out.println("----------------------------------------");
        }
        if (option == 3) {
            System.out.print("Ingresar Año Pub. : ");
            String anioPublicacion = scanner.nextLine();
            l.setAnioPublicacion(anioPublicacion);
            libroDAO.editarLibro(id, l);
            System.out.println("Se actualizó el Año Pub. correctamente.");
            System.out.println("----------------------------------------");
        }
        if (option == 4) {
            System.out.print("Ingresar Género: ");
            String genero = scanner.nextLine();
            l.setGenero(genero);
            libroDAO.editarLibro(id, l);
            System.out.println("Se actualizó el género correctamente.");
            System.out.println("----------------------------------------");
        }
        if (option == 5) {
            System.out.print("Ingresar Cant. Disponible: ");
            Integer cantidad_disponible = scanner.nextInt();
            l.setCantidadDisponible(cantidad_disponible);
            libroDAO.editarLibro(id, l);
            System.out.println("Se actualizó la cantidad disponible correctamente.");
            System.out.println("----------------------------------------");
        }
    }

    public void deleteLibro(Scanner scanner) {
        System.out.print("Ingrese id de libro a eliminar: ");
        Long id = scanner.nextLong();
        libroDAO.eliminarLibro(id);
        System.out.println("Se eliminó el libro correctamente.");
        System.out.println("----------------------------------------");
    }
}

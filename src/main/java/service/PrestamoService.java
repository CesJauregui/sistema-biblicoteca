package service;

import dao.LibroDAO;
import dao.PrestamoDAO;
import entity.Libro;
import entity.Prestamo;
import enums.EstadoPrestamo;

import java.util.*;

public class PrestamoService {
    PrestamoDAO prestamoDAO = new PrestamoDAO();

    public void newPrestamo(Scanner scanner) {
        System.out.print("Ingresar libro (ID): ");
        Long libroId = scanner.nextLong();
        Optional<Libro> li = Optional.ofNullable(new LibroDAO().obtenerLibroPorID(libroId));

        if (li.isPresent()) {
            if (li.get().getCantidadDisponible() > 0) {
                System.out.print("Ingresar usuario (ID): ");
                Long usuarioId = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Ingresar fecha de préstamo: ");
                String fechaPrestamo = scanner.nextLine();

                System.out.print("Ingresar fecha de devolución: ");
                String fechaDevolucion = scanner.nextLine();

                Prestamo prestamo = new Prestamo(usuarioId, libroId, fechaPrestamo, fechaDevolucion, EstadoPrestamo.PRESTADO);
                prestamoDAO.insertarPrestamo(prestamo);

                li.ifPresent(libro -> {
                    libro.setCantidadDisponible(li.get().getCantidadDisponible() - 1);
                    new LibroDAO().editarLibro(li.get().getId(), libro);
                });

                System.out.println("----------------------------------------");
            } else {
                System.out.println("No hay libros disponible.");
            }
        } else {
            System.out.println("El libro no existe, por favor ingrese otro.");
        }
    }

    public void devolucion(Scanner scanner) {
        System.out.print("Ingresar ID de préstamo: ");
        Long prestamoId = null;

        try {
            prestamoId = scanner.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("⚠️ Debe ingresar un número válido para el ID.");
            scanner.nextLine(); // limpiar la entrada incorrecta
            return; // salir del método
        }

        Optional<Prestamo> prestamo = Optional.ofNullable(new PrestamoDAO().getPrestamoPorID(prestamoId));

        if (prestamo.isPresent()) {
            PrestamoDAO prestamoDAO = new PrestamoDAO();
            LibroDAO libroDAO = new LibroDAO();

            prestamo.ifPresent(pres -> {
                pres.setEstado(EstadoPrestamo.DEVUELTO);
                prestamoDAO.editarPrestamo(pres.getId(), pres);

                Optional<Libro> li = Optional.ofNullable(libroDAO.obtenerLibroPorID(pres.getLibroId()));
                li.ifPresentOrElse(libro -> {
                    libro.setCantidadDisponible(libro.getCantidadDisponible() + 1);
                    libroDAO.editarLibro(libro.getId(), libro);
                }, () -> System.out.println("⚠️ El libro asociado al préstamo no existe."));
            });
            System.out.println("✅ Devolución registrada con éxito.");
        } else {
            System.out.println("⚠️ El préstamo no existe, por favor ingrese otro.");
        }
    }

    @SafeVarargs
    static <T> List<String> validarCampos(T... campos) {
        List<String> errores = new ArrayList<>();

        for (int i = 0; i < campos.length; i++) {
            T campo = campos[i];

            if (campo == null) {
                errores.add("Campo " + (i + 1) + " es nulo");
            } else if (campo instanceof String && ((String) campo).trim().isEmpty()) {
                errores.add("Campo " + (i + 1) + " está vacío");
            }
            // Si es otro tipo (Integer, Double, etc.), no hace falta validación extra
        }
        return errores;
    }
}

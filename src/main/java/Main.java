import service.LibroService;
import service.PrestamoService;
import service.ReporteService;
import service.UsuarioService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        LibroService libroService = new LibroService();
        PrestamoService prestamoService = new PrestamoService();
        ReporteService reporteService = new ReporteService();
        Scanner scanner = new Scanner(System.in);

        int actionMain = 0;

        do {
            System.out.println("""
                    ┌------------------------------------------┐
                    │     Sistema de Gestión de Biblioteca     │
                    └------------------------------------------┘
                    """);
            if (actionMain == 0) {
                System.out.println("""
                        ¿A qué módulo quiere ingresar?
                        1. Gestión de Usuarios
                        2. Gestión de Libros
                        3. Gestión de Préstamos
                        4. Reportes
                        5. Salir
                        """);
                System.out.print("Opción: ");
                actionMain = scanner.nextInt();
                scanner.nextLine();

                if (actionMain == 1) {
                    int action;
                    System.out.println("""
                            ┌------------------------------------------┐
                            │            Módulo de Usuarios            │
                            └------------------------------------------┘
                            """);
                    do {
                        System.out.println("""
                                ¿Qué acción quiere hacer? Elija la opción:
                                1. Insertar usuario
                                2. Obtener todos los usuarios
                                3. Editar usuario
                                4. Eliminar usuario
                                5. Ir a menú principal
                                """);

                        System.out.print("Opción: ");
                        action = scanner.nextInt();
                        scanner.nextLine();

                        if (action == 1) {
                            usuarioService.newUsuario(scanner);
                        } else if (action == 2) {
                            usuarioService.getUsuarios();
                        } else if (action == 3) {
                            usuarioService.editUsuario(scanner);
                        } else if (action == 4) {
                            usuarioService.deleteUsuario(scanner);
                        }
                    } while (action != 5);
                    actionMain = 0;

                } else if (actionMain == 2) {
                    int action;
                    System.out.println("""
                            ┌------------------------------------------┐
                            │             Módulo de Libros             │
                            └------------------------------------------┘
                            """);
                    do {
                        System.out.println("""
                                ¿Qué acción quiere hacer? Elija la opción:
                                1. Alta de libro
                                2. Obtener todos los libros
                                3. Editar libro
                                4. Baja de libro
                                5. Ir a menú principal
                                """);

                        System.out.print("Opción: ");
                        action = scanner.nextInt();
                        scanner.nextLine();

                        if (action == 1) {
                            libroService.newLibro(scanner);
                        } else if (action == 2) {
                            libroService.getLibros();
                        } else if (action == 3) {
                            libroService.editLibro(scanner);
                        } else if (action == 4) {
                            libroService.deleteLibro(scanner);
                        }

                    } while (action != 5);
                    actionMain = 0;

                } else if (actionMain == 3) {
                    int action;
                    System.out.println("""
                            ┌-------------------------------------------┐
                            │            Módulo de Préstamos            │
                            └-------------------------------------------┘
                            """);
                    do {
                        System.out.println("""
                                ¿Qué acción quiere hacer? Elija la opción:
                                1. Registrar préstamo
                                2. Registrar devolución
                                3. Ir a menú principal
                                """);

                        System.out.print("Opción: ");
                        action = scanner.nextInt();
                        scanner.nextLine();

                        if (action == 1) {
                            prestamoService.newPrestamo(scanner);
                        } else if (action == 2) {
                            prestamoService.devolucion(scanner);
                        }
                    } while (action != 3);
                    actionMain = 0;

                } else if (actionMain == 4) {
                    int action;
                    System.out.println("""
                            ┌-------------------------------------------┐
                            │            Módulo de Reportes             │
                            └-------------------------------------------┘
                            """);
                    do {
                        System.out.println("""
                                ¿Qué acción quiere hacer? Elija la opción:
                                1. Libros disponibles
                                2. Libros prestados actualmente
                                3. Historial de préstamos por usuario
                                4. Ir a menú principal
                                """);
                        System.out.print("Opción: ");
                        action = scanner.nextInt();
                        scanner.nextLine();

                        if (action == 1) {
                            reporteService.librosDisponible();
                        } else if (action == 2) {
                            reporteService.LibrosPrestados();
                        } else if (action == 3) {
                            reporteService.prestamosPorUsuario(scanner);
                        }
                    } while (action != 4);
                    actionMain = 0;
                }
            }
        } while (actionMain != 5);
        scanner.close();
    }
}

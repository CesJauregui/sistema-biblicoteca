import service.UsuarioService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        Scanner scanner = new Scanner(System.in);

        int action;
        do {
            System.out.println("""
                    ¿Qué acción quiere hacer? Elija la opción:
                    1. Insertar usuario
                    2. Obtener todos los usuarios
                    3. Editar usuario
                    4. Eliminar usuario
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
        } while (action != 0);
        scanner.close();
    }
}

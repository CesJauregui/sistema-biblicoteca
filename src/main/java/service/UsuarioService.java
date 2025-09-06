package service;

import dao.UsuarioDAO;
import entity.Usuario;
import enums.Rol;

import java.util.Scanner;

public class UsuarioService {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void newUsuario(Scanner scanner){
        System.out.print("Ingresar nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingresar correo: ");
        String email = scanner.nextLine();
        System.out.print("Ingresar rol (ADMIN/LECTOR): ");
        Rol rol = Rol.valueOf(scanner.nextLine().toUpperCase());

        Usuario usuario = new Usuario(nombre, email, rol);
        usuarioDAO.insertarUsuario(usuario);
        System.out.println("----------------------------------------");
    }

    public void getUsuarios() {
        System.out.println("Mostrando listado de usuarios...");
        System.out.println("┌---┬---------------------┬------------------------------------┬------------┐");
        System.out.println("│ID │ Nombre              │ Email                              │ Rol        │");
        System.out.println("├---┼---------------------┼------------------------------------┼------------┤");

        for (Usuario u : usuarioDAO.obtenerUsuarios()) {
            System.out.printf("│%-3s│ %-20s│ %-35s│ %-11s│%n",
                    u.getId(),
                    u.getNombre(),
                    u.getEmail(),
                    u.getRol());
        }

        System.out.println("└---┴---------------------┴------------------------------------┴------------┘");
        System.out.println("Fin lista de usuarios.");
    }

    public Usuario getUsuarioPorID(Scanner scanner) {
        System.out.print("Ingrese el id de Usuario: ");
        Long id = scanner.nextLong();

        Usuario u = usuarioDAO.obtenerUsuarioPorID(id);
        System.out.println("┌---┬---------------------┬------------------------------------┬------------┐");
        System.out.println("│ID │ Nombre              │ Email                              │ Rol        │");
        System.out.println("├---┼---------------------┼------------------------------------┼------------┤");
        System.out.printf("│%-3s│ %-20s│ %-35s│ %-11s│%n",
                u.getId(),
                u.getNombre(),
                u.getEmail(),
                u.getRol());
        System.out.println("└---┴---------------------┴------------------------------------┴------------┘");
        return u;
    }

    public void editUsuario(Scanner scanner) {
        Usuario u = this.getUsuarioPorID(scanner);
        Long id = u.getId();

        System.out.println("¿Qué información quiere editar?");
        System.out.println("1 = Nombre, 2 = Email, 3 = Rol");

        System.out.print("Opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            System.out.print("Ingresar nombre: ");
            String nombre = scanner.nextLine();
            u.setNombre(nombre);
            usuarioDAO.editarUsuario(id, u);
            System.out.println("Se actualizó el nombre correctamente.");
            System.out.println("----------------------------------------");
        }
        if (option == 2) {
            System.out.print("Ingresar correo: ");
            String email = scanner.nextLine();
            u.setEmail(email);
            usuarioDAO.editarUsuario(id, u);
            System.out.println("Se actualizó el email correctamente.");
            System.out.println("----------------------------------------");
        }
        if (option == 3) {
            System.out.print("Ingresar rol (ADMIN/LECTOR): ");
            Rol rol = Rol.valueOf(scanner.nextLine().toUpperCase());
            u.setRol(rol);
            usuarioDAO.editarUsuario(id, u);
            System.out.println("Se actualizó el rol correctamente.");
            System.out.println("----------------------------------------");
        }
    }

    public void deleteUsuario(Scanner scanner) {
        System.out.print("Ingrese id de usuario a eliminar: ");
        Long id = scanner.nextLong();
        usuarioDAO.eliminarUsuario(id);
        System.out.println("Se eliminó el usuario correctamente.");
        System.out.println("----------------------------------------");
    }
}

package dao;

import conexion.ConexionDB;
import entity.Libro;
import entity.Prestamo;
import enums.EstadoPrestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {
    public List<Libro> getLibrosDisponibles() {
        String sql = "SELECT * FROM libros WHERE cantidad_disponible > ?";
        List<Libro> libros = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, 0);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Libro libro = new Libro(
                            rs.getLong("id"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("anio_publicacion"),
                            rs.getString("genero"),
                            rs.getInt("cantidad_disponible")
                    );
                    libros.add(libro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public List<Libro> getLibrosPrestados() {
        String sql = "SELECT * FROM libros l INNER JOIN prestamos p ON " +
                "l.id = p.libro_id WHERE p.estado = ?";
        List<Libro> libros = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, EstadoPrestamo.PRESTADO.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Libro libro = new Libro(
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("anio_publicacion"),
                            rs.getString("genero"),
                            rs.getInt("cantidad_disponible")
                    );
                    libros.add(libro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public List<Prestamo> getPrestamosPorUsuario(String usuario) {
        String sql = "SELECT * FROM prestamos p INNER JOIN usuarios u ON " +
                "p.usuario_id = u.id WHERE u.nombre = ?";
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Prestamo prestamo = new Prestamo(
                            rs.getString("fecha_prestamo"),
                            rs.getString("fecha_devolucion"),
                            EstadoPrestamo.valueOf(rs.getString("estado"))
                    );
                    prestamo.setNombreUsuario(rs.getString("nombre"));
                    prestamos.add(prestamo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }
}

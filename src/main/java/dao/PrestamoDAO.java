package dao;

import conexion.ConexionDB;
import entity.Prestamo;
import enums.EstadoPrestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrestamoDAO {
    public void insertarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (usuario_id, libro_id, fecha_prestamo, fecha_devolucion, estado) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, prestamo.getUsuarioId());
            stmt.setLong(2, prestamo.getLibroId());
            stmt.setString(3, prestamo.getFechaPrestamo());
            stmt.setString(4, prestamo.getFechaDevolucion());
            stmt.setString(5, prestamo.getEstado().toString());
            stmt.executeUpdate();
            System.out.println("Préstamo registrado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Prestamo getPrestamoPorID(Long id) {
        String sql = "SELECT * FROM prestamos WHERE id = ?";
        Prestamo prestamo = null;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    prestamo = new Prestamo(
                            rs.getLong("id"),
                            rs.getLong("usuario_id"),
                            rs.getLong("libro_id"),
                            rs.getString("fecha_prestamo"),
                            rs.getString("fecha_devolucion"),
                            EstadoPrestamo.valueOf(rs.getString("estado"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prestamo;
    }

    public void editarPrestamo(Long id, Prestamo prestamo) {
        String sql = "UPDATE prestamos SET fecha_prestamo = ?, fecha_devolucion = ?, estado = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prestamo.getFechaPrestamo());
            stmt.setString(2, prestamo.getFechaDevolucion());
            stmt.setString(3, prestamo.getEstado().toString());
            stmt.setLong(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

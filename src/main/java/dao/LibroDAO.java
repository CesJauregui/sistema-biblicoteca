package dao;

import conexion.ConexionDB;
import entity.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    public void insertarLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo,autor,anio_publicacion,genero,cantidad_disponible) " +
                "VALUES (?,?,?,?,?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getAnioPublicacion());
            stmt.setString(4, libro.getGenero());
            stmt.setInt(5, libro.getCantidadDisponible());
            stmt.executeUpdate();
            System.out.println("Libro registrado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Libro> obtenerLibros() {
        String sql = "SELECT * FROM libros";
        List<Libro> libros = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public Libro obtenerLibroPorID(Long id) {
        String sql = "SELECT * FROM libros WHERE id = ?";
        Libro libro = null;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    libro = new Libro(
                            rs.getLong("id"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("anio_publicacion"),
                            rs.getString("genero"),
                            rs.getInt("cantidad_disponible")
                    );
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }

    public void editarLibro(Long id, Libro libros) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, anio_publicacion = ?, " +
                "genero = ?, cantidad_disponible = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, libros.getTitulo());
            stmt.setString(2, libros.getAutor());
            stmt.setString(3, libros.getAnioPublicacion());
            stmt.setString(4, libros.getGenero());
            stmt.setInt(5, libros.getCantidadDisponible());
            stmt.setLong(6,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarLibro(Long id) {
        String sql = "DELETE FROM libros where id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package dao;

import conexion.ConexionDB;
import entity.Usuario;
import enums.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre,email,rol) VALUES (?,?,?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getRol().toString());
            stmt.executeUpdate();
            System.out.println("Usuario registrado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> obtenerUsuarios() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        Rol.valueOf(rs.getString("rol"))
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario obtenerUsuarioPorID(Long id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()){
                    while (rs.next()){
                        usuario = new Usuario(
                                rs.getLong("id"),
                                rs.getString("nombre"),
                                rs.getString("email"),
                                Rol.valueOf(rs.getString("rol"))
                        );
                    }
                }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void editarUsuario(Long id, Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, rol = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getRol().toString());
            stmt.setLong(4,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(Long id) {
        String sql = "DELETE FROM usuarios where id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

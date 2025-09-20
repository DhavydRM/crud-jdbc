package dao;

import entidade.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private Usuario usuario;

    public UsuarioDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarUsuario(Usuario usuario) {

        try {
            ps = conexao.prepareStatement("INSERT INTO usuarios (nome, email, senha)" +
                    "VALUES (?, ?, ?)");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Deu ruim na Query");
        }

    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            ps = conexao.prepareStatement("SELECT * FROM usuarios");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nome = resultSet.getString(2);
                String email = resultSet.getString(3);
                String senha = resultSet.getString(4);
                usuarios.add(new Usuario(id, nome, email, senha));

            }
        } catch (SQLException e) {
            System.out.println("Deu ruim na Query!");
        }

        return usuarios;
    }

    public Usuario getUsuarioById(int id) {
        try {
            ps = conexao.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            Usuario user = null;

            while (resultSet.next()) {
                user = new Usuario(resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("email"),
                        resultSet.getString("senha"));
            }

            return user;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}

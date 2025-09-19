package conexaodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConexaoMysql {

    private static String status = "Desconectado...";

    public ConexaoMysql() {
    }

    public static Connection getConexao() {
        Connection conexao = null;

        try {
            String nomeDrive = "com.mysql.cj.jdbc.Driver";
            Class.forName(nomeDrive);

            String urlBanco = "jdbc:mysql://localhost/crudjdbc";
            String usuario = "root";
            String senha = "";

            conexao = DriverManager.getConnection(urlBanco, usuario, senha);

            if (Objects.nonNull(conexao)) {
                status = "CONECTADOOOOOO!!";
            } else {
                status = "Conectou não paizão :/";
            }

            return conexao;

        } catch (ClassNotFoundException e) {
            System.out.println("Deu ruim no driver!");
            return null;
        } catch (SQLException e) {
            System.out.println("Erro na conexão");
            return null;
        }
    }

    public static String getStatus() {
        return status;
    }

    public static boolean fecharConexao() {
        try {
            ConexaoMysql.getConexao().close();
            return true;

        } catch (SQLException e) {
            System.out.println("Deu ruim na hora de fechar");
            return false;
        }
    }

    public static Connection reiniciarConexao() {
        fecharConexao();

        return getConexao();
    }
}

import conexaodb.ConexaoMysql;
import dao.UsuarioDao;
import entidade.Usuario;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class ProgramaPrincipal {

    public static void main(String[] args) {

        Usuario usuario = new Usuario(0, "Let", "let@gmail.com", "098765");
        List<Usuario> usuariosList;
        int id = 1;

        Connection conexao = ConexaoMysql.getConexao();
        UsuarioDao usuarioDao = new UsuarioDao(conexao);

        System.out.println(ConexaoMysql.getStatus());

        if (Objects.nonNull(conexao)) {
            Usuario user = usuarioDao.criarUsuario(usuario);
            System.out.println(user);

            usuarioDao.deletarUsuario(user.getId());
        }
    }
}

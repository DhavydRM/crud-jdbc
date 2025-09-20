import conexaodb.ConexaoMysql;
import dao.UsuarioDao;
import entidade.Usuario;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class ProgramaPrincipal {

    public static void main(String[] args) {

        Usuario usuario = new Usuario(0, "Pedro", "pedrin@gmail.com", "123456");
        List<Usuario> usuariosList;

        Connection conexao = ConexaoMysql.getConexao();
        UsuarioDao usuarioDao = new UsuarioDao(conexao);

        System.out.println(ConexaoMysql.getStatus());

        if (Objects.nonNull(conexao)) {
           Usuario user = usuarioDao.getUsuarioById(1);
           System.out.println(user);
        }
    }
}

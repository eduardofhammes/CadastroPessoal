
package dao;

/**
 *
 * @author eduardo
 */
import apoio.ConexaoBD;
import entidade.Pessoa;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PessoaDAO {

    public boolean salvar(Pessoa p) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "insert into pessoa values ("
                    + "default,"
                    + "'" + p.getNome() + "', "
                    + "'" + p.getEmail() + "', "
                    + "'" + p.getTelefone() + "', "
                    + "'" + p.getDataNascimento() + "')";

            System.out.println("SQL: " + sql);

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Pessoa: " + e);
            return false;
        }
    }

    public ArrayList consultar() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * from pessoa order by id";

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Pessoa p = new Pessoa();

                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setEmail(resultado.getString("email"));
                p.setTelefone(resultado.getString("telefone"));
                p.setDataNascimento(resultado.getString("data_nascimento"));

                pessoas.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consular Pessoa: " + e);
        }

        return pessoas;
    }

    public Pessoa consultar(int id) {
        Pessoa pessoa = new Pessoa();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * from pessoa "
                    + "where "
                    + "id = " + id;

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            resultado.next();
            
            pessoa.setId(resultado.getInt("id"));
            pessoa.setNome(resultado.getString("nome"));
            pessoa.setEmail(resultado.getString("email"));
            pessoa.setTelefone(resultado.getString("telefone"));
            pessoa.setDataNascimento(resultado.getString("data_nascimento"));

        } catch (Exception e) {
            System.out.println("Erro ao consular UMA Pessoa: " + e);
        }

        return pessoa;
    }

    public boolean excluir(int id) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "delete from pessoa "
                    + "where "
                    + "id = " + id;

            System.out.println("SQL: " + sql);

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao excluir Pessoa: " + e);
            return false;
        }
    }

    public boolean atualizar(Pessoa p) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "update pessoa "
                    + "set "
                    + "nome = '" + p.getNome() + "', "
                    + "email = '" + p.getEmail() + "', "
                    + "telefone = '" + p.getDataNascimento() + "' "
                    + "where id = " + p.getId();

            System.out.println("SQL: " + sql);

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar Pessoa: " + e);
            return false;
        }
    }
}

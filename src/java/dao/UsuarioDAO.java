/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Usuario;
import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO {
    
    
    public boolean autenticar(Usuario u){
        // tenta encontrar no banco de dados um usuario com o usuario(através do u.getEmail()) e 
        // senha já criptografada com md5 ('u.getSenha') e usuários com situação ativas(a)
        // só dará certo se ambas forem verdadeiras(AND na consulta)
        try {
            String sql = "SELECT nome, senha from usuario " +
                    "WHERE nome = '"+ u.getUsuario() + "' AND senha = md5('" +
                    u.getSenha() + "')";
                    
            // print no console do sql rodado no BD        
            System.out.println("SQL: " + sql);
            
            // executando a query no banco de dados
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            // Se retornou alguma coisa com .next(), significado que este é usuário que tem o mesmo senha e login registrado
            // assim, será feito o acesso com o return true;
            if (resultadoQ.next()) {
                return true;
            } else {
                return false; // se não encontrou nenhum registro de email e senha no BD, retorna false e impede o acesso
            }
        } catch(Exception e) {
            System.err.println(e);
            return false; // significa que não foi possível autenticar
        }
        
    }
    
}

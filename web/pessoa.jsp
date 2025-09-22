

<%@page import="dao.PessoaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pessoas</title>
    </head>
    <%
        Pessoa p = (Pessoa) request.getAttribute("pessoa");

        if (p == null) {
            p = new Pessoa();
            p.setId(0);
            p.setNome("");
            p.setEmail("");
            p.setTelefone("");
            p.setDataNascimento("");
            
        }
    %>

    <body>

        <%@include file="menu.jsp" %>
        
        <h1>Cadastro de pessoas</h1>

        <form method="post" action="acao?a=salvarPessoa">
            <label for="id">Id</label><br>
            <input type="text" id="id" name="id" readonly="" value="<%= p.getId()%>"><br>

            <label for="nome">Nome</label><br>
            <input type="text" id="nome" name="nome" value="<%= p.getNome()%>"><br>
            
            <label>E-mail</label><br>
            <input type="text" id="email" name="email" value="<%= p.getEmail() %>"><br>
            
            <label>Telefone</label><br>
            <input type="tel" id="telefone" name="telefone" value="<%= p.getTelefone() %>"><br>
            
            <label>Data de nascimento</label><br>
            <input type="text" id="data_nascimento" name="data_nascimento" value="<%= p.getDataNascimento()%>"><br>
            
            <br>
            <input type="submit" name="salvar" value="Salvar" class="btn btn-success">
        </form>

        <hr></hr>    
        <h3>Listagem das pessoas</h3>

        <%
            ArrayList<Pessoa> pessoas = new PessoaDAO().consultar();
        %>

        <table class="table">
            <th>Id</th>
            <th>Nome</th>
            <th>E-mail</th>
            <th>Telefone</th>
            <th>Data de Nascimento</th>
            <th>Editar</th>
            <th>Excluir</th>
                <%
                    for (int i = 0; i < pessoas.size(); i++) {
                %>
            <tr>
                <td><%= pessoas.get(i).getId()%></td>
                <td><%= pessoas.get(i).getNome()%></td>
                <td><%= pessoas.get(i).getEmail()%></td>
                <td><%= pessoas.get(i).getTelefone()%></td>
                <td><%= pessoas.get(i).getDataNascimento()%></td>
                <td><a href="acao?a=editarPessoa&id=<%= pessoas.get(i).getId()%>" class="btn btn-success">Editar</a></td>
                <td><a href="acao?a=excluirPessoa&id=<%= pessoas.get(i).getId()%>" class="btn btn-danger">Excluir</a></td>
            </tr>
            <%
                }
            %>

        </table>
    </body>
</html>

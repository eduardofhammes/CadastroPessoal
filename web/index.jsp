
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

    </head>
    
    <!-- quando for iniciado o projeto ou acessado a url, será redirecionado para a página de login
    diretamente, de acordo com o trecho abaixo -->
    <%
        response.sendRedirect("login.jsp");
    %>
    <body>            

    </body>
</html>

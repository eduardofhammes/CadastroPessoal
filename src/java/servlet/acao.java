/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.PessoaDAO;
import dao.UsuarioDAO;
import entidade.Pessoa;
import entidade.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class acao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet acao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

        // -------------------------------------------------------------------
        String a = request.getParameter("a");
        
        if(a.equals("editarPessoa")) {
            String codigo = request.getParameter("id");
            int id = Integer.parseInt(codigo);
            
            Pessoa pessoa = new PessoaDAO().consultar(id);
            
            request.setAttribute("pessoa", pessoa);
            
            encaminharPagina("pessoa.jsp", request, response);
        }
        
        if(a.equals("excluirPessoa")) {
            String codigo = request.getParameter("id");
            int id = Integer.parseInt(codigo);
            
            if(new PessoaDAO().excluir(id)) {
                encaminharPagina("sucesso.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

        System.out.println("Estou no POST.");

        String primeiroNome = request.getParameter("fname");
        String segundoNome = request.getParameter("lname");

        System.out.println("Primeiro nome: " + primeiroNome);
        System.out.println("Segundo nome: " + segundoNome);
        

        // if (new UsuarioDAO().autenticar(primeiroNome, segundoNome) != null) {
        //    response.sendRedirect("sucesso.jsp");
          //  request.setAttribute("xxx", 1); // exemplo
          //   encaminharPagina("sucesso.jsp", request, response);
        // } else {
            //  encaminharPagina("erro.jsp", request, response);
        // }
        // encaminhamentos comentados na U7 apos implementacao do LOGIN

        //---------------------------------------------------------------------
        String a = request.getParameter("a");

        
        if (a.equals("salvarPessoa")) {
            String codigo = request.getParameter("id");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            String dataNascimento = request.getParameter("data_nascimento");
            
            int id = Integer.parseInt(codigo);
            
            Pessoa pessoa = new Pessoa();
            pessoa.setId(id);
            pessoa.setNome(nome);
            pessoa.setEmail(email);
            pessoa.setTelefone(telefone);
            pessoa.setDataNascimento(dataNascimento);
            
            // salvar
            if(id == 0) {
                if (new PessoaDAO().salvar(pessoa)){
                    encaminharPagina("sucesso.jsp", request, response);
                } else {
                    encaminharPagina("erro.jsp", request, response);
                }
            // atualizar  
            } else {
                if(new PessoaDAO().atualizar(pessoa)) {
                    encaminharPagina("sucesso.jsp", request, response);
                } else {
                    encaminharPagina("erro.jsp", request, response);
                }
            }
        }

        // quando a == login
        // isso quer dizer que veio da página de login da aplicação
        if (a.equals("login")) {
            // logica do login
            // pegar usuario
            // pegar senha
            // autenticar = verificar
            // sucesso = vai pro sistema || erro = login de novo

            String user = request.getParameter("user");
            String passwd = request.getParameter("passwd");

            System.out.println("User: " + user);
            System.out.println("Passwd: " + passwd);
            
            Usuario usuario = new Usuario();
            usuario.setUsuario(user);
            usuario.setSenha(passwd);

            if(new UsuarioDAO().autenticar(usuario)) {
                HttpSession sessao = request.getSession();
                sessao.setAttribute("user", usuario);

                encaminharPagina("menu.jsp", request, response);
            } else {
                String erro = "erro-login";
                request.setAttribute("erro", erro);
                System.out.println("Não foi possível fazer login!");
                encaminharPagina("login.jsp", request, response);
            }
        }

        if (a.equals("logout")) {
            HttpSession sessao = request.getSession();
            sessao.invalidate();

            response.sendRedirect("login.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar: " + e);
        }
    }

}

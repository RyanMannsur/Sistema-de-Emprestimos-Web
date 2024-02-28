/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Usuario;

/**
 *
 * @author ryane
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    UsuarioDAO userDAO = new UsuarioDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<h1>Fazer Login</h1>");
            out.println("<form method=\"POST\">");
            out.println("<p><label for=\"email\">Email: </label><input type=\"email\" name=\"email\" required></p>");
            out.println("<p><label for=\"senha\">Senha: </label><input type=\"password\" name=\"senha\" required></p>");
            out.println("<input type=\"submit\" value=\"Enviar\">");
            out.println("</form>");
            out.println("<button onclick=\"window.location.href='index.html'\">Voltar</button>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        boolean sucesso = userDAO.logarUsuario(email, senha);
        if (sucesso) {
            response.getWriter().println("<script>window.location.href='emprestimos.html';</script>");
        } else {
            response.getWriter().println("<script>alert('Erro no login. email ou senha invalido!'); window.location.href='index.html';</script>");
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

}

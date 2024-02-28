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

@WebServlet(name = "CadastarUsuarioServlet", urlPatterns = {"/CadastarUsuarioServlet"})
public class CadastarUsuarioServlet extends HttpServlet {

    UsuarioDAO userDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Formulario</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<h1>Cadastre-se</h1>");
            out.println("<form method=\"POST\">");
            out.println("<p><label for=\"nome\">Nome: </label><input type=\"text\" name=\"nome\" required></p>");
            out.println("<p><label for=\"sobrenome\">Sobrenome: </label><input type=\"text\" name=\"sobrenome\" required></p>");
            out.println("<p><label for=\"cpf\">CPF: </label><input type=\"text\" name=\"cpf\" required></p>");
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
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Usuario user = new Usuario(nome, sobrenome, email, senha, cpf);
        boolean sucesso = userDAO.adiconarUsuarios(user);
        if (sucesso) {
            response.getWriter().println("<script>alert('Cadastro feito com sucesso'); window.location.href='index.html';</script>");
        } else {
            response.getWriter().println("<script>alert('Erro no cadastro. Verifique se o email ou cpf ja está cadastrado!'); window.location.href='index.html';</script>");
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

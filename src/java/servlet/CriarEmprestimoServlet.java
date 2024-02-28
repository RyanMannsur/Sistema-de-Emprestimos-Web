/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.EmprestimoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Emprestimo;

/**
 *
 * @author ryane
 */
@WebServlet(name = "CriarEmprestimoServlet", urlPatterns = {"/CriarEmprestimoServlet"})
public class CriarEmprestimoServlet extends HttpServlet {

    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
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
        Emprestimo e;
        String nome = request.getParameter("nome");
        String turno = request.getParameter("turno");
        String data = request.getParameter("data");
        String horario = request.getParameter("horario");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        e = new Emprestimo(nome,quantidade,data,horario,turno);
        // preencha o objeto Emprestimo 'e' com os dados do formulário
        boolean sucesso = emprestimoDAO.adiconarEmprestimos(e);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado</title>");
            out.println("<script>");
            out.println("window.onload = function() {");
            if (sucesso) {
                out.println("    alert('Emprestimo feito');");
            } else {
                out.println("    alert('Emprestimo não feito');");
            }
            out.println("    window.location.href = 'emprestimos.html';");
            out.println("}");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

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
import java.util.ArrayList;
import model.Emprestimo;

/**
 *
 * @author ryane
 */
@WebServlet(name = "EmprestimosAtivosServlet", urlPatterns = {"/EmprestimosAtivosServlet"})
public class EmprestimosAtivosServlet extends HttpServlet {

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
        ArrayList<Emprestimo> emprestimosAtivos = emprestimoDAO.buscarEmprestimosAtivo();
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Emprestimos Ativos</title>");
            out.println("<script>");
            out.println("function confirmarRecebimento(form) {");
            out.println("    var confirmacao = confirm('Voce realmente deseja receber este emprestimo?');");
            out.println("    if (confirmacao) {");
            out.println("        form.submit();");
            out.println("    }");
            out.println("}");
            out.println("</script>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<h1>Emprestimos Ativos</h1>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Quantidade</th>");
            out.println("<th>Nome</th>");
            out.println("<th>Data</th>");
            out.println("<th>Turno</th>");
            out.println("<th>Receber</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            for (Emprestimo emprestimo : emprestimosAtivos) {
                out.println("<tr>");
                out.println("<td>" + emprestimo.getQuantidade() + "</td>");
                out.println("<td>" + emprestimo.getNome() + "</td>");
                out.println("<td>" + emprestimo.getData() + "</td>");
                out.println("<td>" + emprestimo.getTurno() + "</td>");
                out.println("<td>");
                out.println("<form action=\"EmprestimosAtivosServlet\" method=\"POST\" onsubmit=\"event.preventDefault(); confirmarRecebimento(this);\">");
                out.println("<input type=\"hidden\" name=\"id\" value=\"" + emprestimo.getId() + "\">");
                out.println("<input type=\"submit\" value=\"Receber\">");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("<button onclick=\"window.location.href='emprestimos.html'\">Voltar</button>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
        int idEmprestimo = Integer.parseInt(request.getParameter("id"));
        boolean sucesso = emprestimoDAO.receberEmprestimo(idEmprestimo);
        if (sucesso) {
            response.sendRedirect("EmprestimosAtivosServlet");
        } else {
            response.getWriter().println("<script>alert('Erro! Não foi possivel receber o emprestimo'); window.location.href='EmprestimosAtivosServlet';</script>");
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

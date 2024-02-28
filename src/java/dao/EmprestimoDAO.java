/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Emprestimo;

public class EmprestimoDAO {

    private Connection con;
    Conexao conexao = new Conexao();

    public ArrayList<Emprestimo> buscarEmprestimosAtivo() {
        ArrayList<Emprestimo> emprestimosAtivos = new ArrayList<>();

        try {
            con = conexao.conectar();
            String query = "SELECT * FROM emprestimo WHERE ativo = true";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int quantidade = rs.getInt("quantidade");
                String nome = rs.getString("nome");
                String data = rs.getString("data");
                String turno = rs.getString("turno");
                String horario = rs.getString("horario");

                Emprestimo e = new Emprestimo(nome, quantidade, data, horario, turno, id);

                emprestimosAtivos.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        return emprestimosAtivos;
    }

    public ArrayList<Emprestimo> buscarEmprestimos() {

        ArrayList<Emprestimo> emprestimos = new ArrayList<>();

        try {
            con = conexao.conectar();
            String query = "SELECT * FROM emprestimo";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int quantidade = rs.getInt("quantidade");
                String nome = rs.getString("nome");
                String data = rs.getString("data");
                String turno = rs.getString("turno");
                String horario = rs.getString("horario");

                Emprestimo e = new Emprestimo(nome, quantidade, data, horario, turno, id);

                emprestimos.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        return emprestimos;
    }

    public boolean adiconarEmprestimos(Emprestimo e) {
        boolean sucesso = false;
        try {
            con = conexao.conectar();
            String query = "INSERT INTO emprestimo (nome, quantidade, data, horario, turno, ativo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, e.getNome());
            pst.setInt(2, e.getQuantidade());
            pst.setString(3, e.getData());
            pst.setString(4, e.getHorario());
            pst.setString(5, e.getTurno());
            pst.setBoolean(6, true); // Aqui definimos o campo 'ativo' como true
            pst.executeUpdate();
            sucesso = true;
            return sucesso;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return sucesso;
        }
    }

    public boolean receberEmprestimo(int idEmprestimo) {
        // Atualiza o campo 'ativo' no banco de dados
        boolean sucesso = false;
        try {
            Connection con = conexao.conectar();
            String query = "UPDATE emprestimo SET ativo = false WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idEmprestimo);
            pst.executeUpdate();
            sucesso = true;
            return sucesso;
        } catch (Exception e) {
            e.printStackTrace();
            return sucesso;
        }
    }

    public boolean deletarEmprestimo(int idEmprestimo) {
        // Exclui o registro do banco de dados
        boolean sucesso = false;
        try {
            Connection con = conexao.conectar();
            String query = "DELETE FROM emprestimo WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idEmprestimo);
            pst.executeUpdate();
            sucesso = true;
            return sucesso;
        } catch (Exception e) {
            e.printStackTrace();
            return sucesso;
        }
    }
}

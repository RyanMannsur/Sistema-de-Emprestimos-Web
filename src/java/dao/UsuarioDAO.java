/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Usuario;

public class UsuarioDAO {

    private Connection con;
    Conexao conexao = new Conexao();

    public ArrayList<Usuario> buscarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            con = conexao.conectar();
            String query = "SELECT * FROM usuario";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String cpf = rs.getString("cpf");
                Usuario e = new Usuario(nome, sobrenome, email, senha, cpf);
                usuarios.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public boolean logarUsuario(String email, String senha) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            con = conexao.conectar();
            String query = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, senha);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean adiconarUsuarios(Usuario u) {
        boolean sucesso = false;
        if(u.getNome().equals("") || u.getCpf().equals("") || u.getEmail().equals("") || u.getSenha().equals("") || u.getSobrenome().equals("") || u.getCpf().length()<11)
            return false;
        try {
            Connection con = conexao.conectar();
            String query = "INSERT INTO usuario (nome, sobrenome, cpf, email, senha) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getSobrenome());
            pst.setString(3, u.getCpf());
            pst.setString(4, u.getEmail());
            pst.setString(5, u.getSenha());
            pst.executeUpdate();
            sucesso = true;
            return sucesso;
        } catch (Exception e) {
            e.printStackTrace();
            return sucesso;
        }
    }

    public boolean trocarSenha(String cpf, String senha) {
        boolean sucesso = false;
        try {
            Connection con = conexao.conectar();
            String query = "UPDATE usuario SET senha = ? WHERE cpf = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, senha);
            pst.setString(2, cpf);
            pst.executeUpdate();
            sucesso = true;
            return sucesso;
        } catch (Exception e) {
            e.printStackTrace();
            return sucesso;
        }
    }

    public boolean deletarUsuario(String cpf) {
        // Exclui o registro do banco de dados
        boolean sucesso = false;
        try {
            Connection con = conexao.conectar();
            String query = "DELETE FROM usuario WHERE cpf = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, cpf);
            pst.executeUpdate();
            sucesso = true;
            return sucesso;
        } catch (Exception e) {
            e.printStackTrace();
            return sucesso;
        }
    }
}

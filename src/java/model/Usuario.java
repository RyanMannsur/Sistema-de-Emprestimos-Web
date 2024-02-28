
package model;


public class Usuario {
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String cpf;

    public Usuario(String nome, String sobrenome, String email, String senha, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }
    
    
}

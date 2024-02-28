/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Emprestimo {
    private String nome;
    private int quantidade;
    private String data;
    private String horario;
    private String turno;
    private boolean ativo;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Emprestimo(String nome, int quantidade, String data, String horario, String turno, int id) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.data = data;
        this.horario = horario;
        this.turno = turno;
        this.ativo = true; //Todo emprestimo construido recebe true 
        this.id = id;
    }

    public Emprestimo(String nome, int quantidade, String data, String horario, String turno) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.data = data;
        this.horario = horario;
        this.turno = turno;
        this.ativo = true;
    }
    
    

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }    

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getTurno() {
        return turno;
    }
    
}

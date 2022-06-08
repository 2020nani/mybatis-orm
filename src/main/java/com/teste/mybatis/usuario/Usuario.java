package com.teste.mybatis.usuario;


public class Usuario {

    private String id;
    private String nome;
    private String salario;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSalario() {
        return salario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public Usuario(String id, String nome, String salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }
}

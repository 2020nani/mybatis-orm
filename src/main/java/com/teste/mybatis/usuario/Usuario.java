package com.teste.mybatis.usuario;


import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Usuario {

    @NotBlank
    private String id;
    @NotBlank
    private String nome;

    @NotNull
    private Integer salario;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public Usuario(String id, String nome, Integer salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", salario='" + salario + '\'' +
                '}';
    }
}

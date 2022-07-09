package com.teste.mybatis.usuario;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {

    public String createNewTableIfNotExists(String tableName);

    String save(Usuario usuario);

    List<Usuario> findAll();

    Optional<Usuario> findById(String id);

    void update(String id,String nome);

    List<Usuario> findBySalarioMoreThan(Integer salario);

    String deleteAll();
}

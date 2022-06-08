package com.teste.mybatis.usuario;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsuarioMapper {
    @Insert(" CREATE TABLE IF NOT EXISTS usuario(id varchar NOT NULL," +
            "nome varchar(18) NOT NULL,salario varchar(18) NOT NULL,\n" +
            "        PRIMARY KEY (id)\n" +
            "  )")
    public void createNewTableIfNotExists(String tableName);

    @Insert("insert into usuario(id,nome,salario) values(#{id},#{nome},#{salario})")
    void save(Usuario usuario);

    @Select("select * from usuario")
    List<Usuario> findAll();
}

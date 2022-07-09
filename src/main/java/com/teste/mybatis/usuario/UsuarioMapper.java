package com.teste.mybatis.usuario;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UsuarioMapper {
    @Insert(" CREATE TABLE IF NOT EXISTS usuario(id varchar NOT NULL," +
            "nome varchar(18) NOT NULL,salario integer NOT NULL,\n" +
            "        PRIMARY KEY (id)\n" +
            "  )")
    public void createNewTableIfNotExists(String tableName);

    @Insert("insert into usuario(id,nome,salario) values(#{id},#{nome},#{salario})")
    void save(Usuario usuario);

    @Select("select * from usuario")
    List<Usuario> findAll();

    @Select("select * from usuario where id=#{id}")
    @Results(value = {@Result(property="id", column="usuario_id")})
    Optional<Usuario> findById(String id);

    @Insert("UPDATE usuario SET NOME=#{nome} WHERE ID=#{id}")
    void update(String id,String nome);

    @Select("select * from usuario where salario>=#{salario}")
    List<Usuario> findBySalarioMoreThan(Integer salario);

    @Delete("delete from usuario")
    void deleteAll();



}

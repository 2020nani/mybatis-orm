package com.teste.mybatis;

import com.jcraft.jsch.*;
import com.teste.mybatis.usuario.Usuario;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MappedTypes(Usuario.class)
//@MapperScan("com.teste.mybatis.usuario")
@SpringBootApplication
public class MybatisApplication {

	public static void main(String[] args) {
			SpringApplication.run(MybatisApplication.class, args);
	}
	}

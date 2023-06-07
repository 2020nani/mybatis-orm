package com.teste.mybatis;

import com.jcraft.jsch.*;
import com.teste.mybatis.usuario.Usuario;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;


//@MappedTypes(Usuario.class)
//@MapperScan("com.teste.mybatis.usuario")
@SpringBootApplication
public class MybatisApplication {

	public static void main(String[] args) {


		SpringApplication.run(MybatisApplication.class, args);
	}
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofBytes(166579626L));
		factory.setMaxRequestSize(DataSize.ofBytes(166579626L));
		return factory.createMultipartConfig();
	}
	}

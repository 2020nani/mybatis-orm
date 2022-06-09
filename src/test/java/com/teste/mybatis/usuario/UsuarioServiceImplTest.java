package com.teste.mybatis.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioServiceImplTest {

    @Autowired
    private UsuarioMapper usuarioMapper;

    private UsuarioServiceImpl usuarioService;

    private Usuario usuarioCriado1;

    private Usuario usuarioCriado2;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioServiceImpl(usuarioMapper);
        usuarioCriado1 = new Usuario("1", "hernani", 1000);
        usuarioCriado2 = new Usuario("2", "hernani almeida", 10000);
    }

    @Test
    void criaTabelaCasoNaoExista() {
        String isTabela = usuarioService.createNewTableIfNotExists("usuario");
        assertNotNull(isTabela);
        assertEquals(isTabela,"Tabela criada caso nao exista");
    }

    @Test
    void save() {
        String usuario = usuarioService.save(usuarioCriado1);
        assertNotNull(usuario);
        assertEquals(usuario, usuarioCriado1.toString());
    }

    @Test
    void findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void findBySalarioMoreThan() {
    }
}
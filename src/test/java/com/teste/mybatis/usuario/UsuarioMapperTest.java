package com.teste.mybatis.usuario;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioMapperTest {

    @Autowired
    private UsuarioMapper mapper;

    private Usuario usuarioCriado1;

    private Usuario usuarioCriado2;

    private Usuario usuarioError;

    @BeforeEach
    void setUp() {
        mapper.createNewTableIfNotExists("usuario");
        usuarioCriado1 = new Usuario("1", "hernani", 1000);
        usuarioCriado2 = new Usuario("2", "hernani almeida", 10000);
        usuarioError = new Usuario(null, "teste", 1000);
    }

    @AfterEach
    void dropSetUp() {
        mapper.deleteAll();
    }

    @Test
    void deveSalvarUsuarioBancoDeDados() {
        String testeSalvarUsuario = "";
        try{
            mapper.save(usuarioCriado1);
            testeSalvarUsuario = usuarioCriado1.toString();
        }catch(Exception e){
            testeSalvarUsuario = "Houve um erro";
        }

        assertNotEquals(testeSalvarUsuario,"houve um erro");
        assertEquals(testeSalvarUsuario, usuarioCriado1.toString());
    }

    @Test
    void naoDeveSalvarUsuarioBancoDeDadosIdNulo() {
        String testeSalvarUsuario = "";
        try{
            mapper.save(usuarioError);
            testeSalvarUsuario = usuarioError.toString();
        }catch(Exception e){
            testeSalvarUsuario = "Houve um erro";
        }

        assertEquals(testeSalvarUsuario,"Houve um erro");
        assertNotEquals(testeSalvarUsuario, usuarioCriado1.toString());
    }

    @Test
    @Transactional
    void deveRetornarListaUsuariosCadastrados() {
        mapper.save(usuarioCriado1);
        mapper.save(usuarioCriado2);
        List<Usuario> usuarios = mapper.findAll();
        assertEquals(usuarios.size(), 2);
        assertNotEquals(usuarios.isEmpty(), true);
    }

    @Test
    void deveRetornarUsuarioCadastradoPorId() {
        mapper.save(usuarioCriado1);
        Usuario usuario = mapper.findById("1").get();
        assertNotNull(usuario);
        assertEquals(usuario.getId(), usuarioCriado1.getId());
    }

    @Test
    void deveAtualizarUsuarioCadastradoPorId() {
        mapper.save(usuarioCriado1);
        mapper.update("1", "nomeatualizado");
        Usuario usuarioAtualizado = mapper.findById("1").get();
        assertNotNull(usuarioAtualizado);
        assertNotEquals(usuarioCriado1.getNome(), usuarioAtualizado.getNome());
        assertEquals(usuarioAtualizado.getId(), "1");
        assertEquals(usuarioAtualizado.getNome(), "nomeatualizado");
    }

    @Test
    void deveRetornarUsuarioCadastradoPorValorSalarioMaiorOuIgual() {
        mapper.save(usuarioCriado1);
        mapper.save(usuarioCriado2);
        List<Usuario> usuarios = mapper.findBySalarioMoreThan(2000);
        assertEquals(usuarios.isEmpty(), false);
        assertEquals(usuarios.get(0).getId(), "2");
        assertEquals(usuarios.size(), 1);
    }

    @Test
    void naoDeveRetornarUsuarioCadastradoPorValorSalarioMenor() {
        mapper.save(usuarioCriado1);
        mapper.save(usuarioCriado2);
        List<Usuario> usuarios = mapper.findBySalarioMoreThan(100000);
        assertEquals(usuarios.isEmpty(), true);
    }

    @Test
    void deveDeletarTodosUsuarios() {
        mapper.save(usuarioCriado1);
        mapper.save(usuarioCriado2);
        mapper.deleteAll();
        List<Usuario> usuarios = mapper.findAll();
        assertEquals(usuarios.isEmpty(), true);
    }
}
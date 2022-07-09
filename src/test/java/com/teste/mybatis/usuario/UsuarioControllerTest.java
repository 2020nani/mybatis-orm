package com.teste.mybatis.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMybatis
class UsuarioControllerTest {

    @MockBean
    private UsuarioServiceImpl usuarioServiceImpl;

    @MockBean
    private UsuarioMapper usuarioMapper;

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        Usuario usuario = new Usuario("2","hernani",1000);
        usuarioMapper.save(usuario);
    }


    @Test
    void deveCadastrarUsuario() throws Exception {
        Usuario usuario = new Usuario("1","hernani",1000);
        when(usuarioServiceImpl.save(ArgumentMatchers.any())).thenReturn(usuario.toString());
        String json = mapper.writeValueAsString(usuario);
        this.mockMvc
                .perform(
                        post("/usuario")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(content().string(usuario.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void naoDeveCadastrarUsuarioSalarioNull() throws Exception {
        Usuario usuario = new Usuario("1","hernani",null);
        when(usuarioServiceImpl.save(ArgumentMatchers.any())).thenReturn(usuario.toString());
        String json = mapper.writeValueAsString(usuario);
        this.mockMvc
                .perform(
                        post("/usuario")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(content().json("{\"globalErrorMessages\":[],\"numberOfErrors\":1,\"errors\":[{\"field\":\"salario\",\"message\":\"must not be null\"}]}"))
                .andExpect(__ -> Assert.assertThat(
                        __.getResolvedException(),
                        CoreMatchers.instanceOf(MethodArgumentNotValidException.class)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarListaUsuarios() throws Exception {
        Usuario usuario = new Usuario("1","hernani",1000);
        Usuario usuario2 = new Usuario("2","junior",10000);
        List<Usuario> usuarioList = Arrays.asList(usuario,usuario2);
        when(usuarioServiceImpl.findAll()).thenReturn(usuarioList);
        this.mockMvc
                .perform(
                        get("/all")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(status().isOk());
    }

    @Test
    void DeveRetornarListaVaziaSeNaoUsuariosCadastrado() throws Exception {
        List<Usuario> usuarioList = new ArrayList<>();
        when(usuarioMapper.findAll()).thenReturn(usuarioList);
        this.mockMvc
                .perform(
                        get("/all")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().json("[]"))
                .andExpect(status().isOk())
                .andReturn();
        verify(usuarioMapper, times(1)).findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void findBySalarioMoreThan() {
    }

    @Test
    void testFindById() {
    }

    @Test
    void deleteAll() {
    }
}
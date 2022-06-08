package com.teste.mybatis.usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    private UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping("/usuario")
    public String criaUsuario(@RequestBody Usuario usuario){
        try {
            usuarioMapper.createNewTableIfNotExists("usuario");
            usuarioMapper.save(usuario);
            return "Deu bom";
        }catch (Exception e) {
            System.out.println(e);
            return "falhou";
        }
    }

    @GetMapping("/all")
    public List<Usuario> findall(){
        usuarioMapper.createNewTableIfNotExists("usuario");
        return usuarioMapper.findAll();
    }
}

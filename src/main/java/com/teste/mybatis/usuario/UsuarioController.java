package com.teste.mybatis.usuario;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UsuarioController {

    private UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping("/usuario")
    public String criaUsuario(@RequestBody Usuario usuario){
        try {
            String id = UUID.randomUUID().toString();
            usuarioMapper.createNewTableIfNotExists("usuario");
            if(usuario.getId() == null){
                usuario.setId(id);
            }
            usuarioMapper.save(usuario);
            return usuario.toString();
        }catch (Exception e) {
            return "falhou";
        }
    }

    @GetMapping("/all")
    public List<Usuario> findall(){
        usuarioMapper.createNewTableIfNotExists("usuario");
        return usuarioMapper.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<Usuario> findById(@PathVariable String id){
            return Optional.ofNullable(usuarioMapper.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario cadastrado com este id")));


    }

    @GetMapping("/findBySalarioMoreThan/{salario}")
    public List<Usuario> findBySalarioMoreThan(@PathVariable Integer salario){
        return usuarioMapper.findBySalarioMoreThan(salario);



    }

    @PutMapping("/edit/{id}/{nome}")
    public String findById(@PathVariable String id, @PathVariable String nome){
        Usuario usuario = usuarioMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario cadastrado com este id"));
        usuarioMapper.update(id,nome);
        return "Usuario com id " + id + " atualizado com sucesso";
    }
}

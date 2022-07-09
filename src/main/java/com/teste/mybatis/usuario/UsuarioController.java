package com.teste.mybatis.usuario;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class UsuarioController {

    private UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/usuario")
    public String criaUsuario(@RequestBody @Valid Usuario usuario){
            String id = UUID.randomUUID().toString();
            if(usuario.getId() == null){
                usuario.setId(id);
            }

            return usuarioService.save(usuario);
    }

    @GetMapping("/all")
    public List<Usuario> findall(){
        usuarioService.createNewTableIfNotExists("usuario");
        return usuarioService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<Usuario> findById(@PathVariable String id){
            return Optional.ofNullable(usuarioService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario cadastrado com este id")));
    }

    @GetMapping("/findBySalarioMoreThan/{salario}")
    public List<Usuario> findBySalarioMoreThan(@PathVariable Integer salario){
        return usuarioService.findBySalarioMoreThan(salario);

    }

    @PutMapping("/edit/{id}/{nome}")
    public String findById(@PathVariable String id, @PathVariable String nome){
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario cadastrado com este id"));
        usuarioService.update(id,nome);
        return "Usuario com id " + id + " atualizado com sucesso";
    }

    @DeleteMapping("/usuario")
    public String deleteAll(){
        return usuarioService.deleteAll();
    }
}

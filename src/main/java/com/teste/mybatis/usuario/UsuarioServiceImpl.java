package com.teste.mybatis.usuario;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioMapper mapper;

    public UsuarioServiceImpl(UsuarioMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String createNewTableIfNotExists(String tableName) {

        try {
            mapper.createNewTableIfNotExists("usuario");
            return "Tabela criada caso nao exista";
        }catch (Exception e){
            return "Houve um erro";
        }
    }

    @Override
    public String save(Usuario usuario) {
       try {
           mapper.createNewTableIfNotExists("usuario");
           mapper.save(usuario);
           return usuario.toString();
       }catch (Exception e){
           return e.getMessage();
       }

    }

    @Override
    public List<Usuario> findAll() {
        return mapper.findAll();
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return Optional.ofNullable(mapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario cadastrado com este id")));
    }

    @Override
    public void update(String id, String nome) {
          mapper.update(id, nome);
    }

    @Override
    public List<Usuario> findBySalarioMoreThan(Integer salario) {
        return mapper.findBySalarioMoreThan(salario);
    }

    @Override
    public String deleteAll() {
        try {
            mapper.deleteAll();
            return "Usuarios deletados com sucesso";
        }catch(Exception e){
            return e.getMessage();
        }
    }
}

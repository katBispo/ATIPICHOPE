package com.atipic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atipic.model.Usuario;
import com.atipic.repository.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios") // Prefixo para todas as rotas deste controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Criar novo usuário
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    // Listar todos os usuários
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodos();
    }

    // Buscar um usuário por ID
    @GetMapping("/{id}")
    public Optional<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    // Atualizar um usuário
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioService.buscarPorId(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setIdade(usuarioAtualizado.getIdade());
            usuario.setSenha(usuarioAtualizado.getSenha());
            return usuarioService.salvarUsuario(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }

    // Deletar um usuário por ID
    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        if (usuario.isPresent()) {
            usuarioService.deletarUsuario(usuario.get());
            return "Usuário deletado com sucesso!";
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }
}

package com.atipic.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atipic.model.Usuario;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioDAOImpl usuarioDAO;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

    public void deletarUsuario(Usuario usuario) {
        usuarioDAO.delete(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioDAO.findById(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll();
    }
}

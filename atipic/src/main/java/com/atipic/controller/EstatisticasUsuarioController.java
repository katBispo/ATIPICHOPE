package com.atipic.controller;

import com.atipic.model.EstatisticasUsuario;
import com.atipic.repository.EstatisticasUsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasUsuarioController {

    @Autowired
    private EstatisticasUsuarioDAO estatisticasUsuarioDAO;

    // Endpoint para salvar ou atualizar estatísticas do usuário
    @PostMapping
    public ResponseEntity<EstatisticasUsuario> salvarEstatisticas(@RequestBody EstatisticasUsuario estatisticasUsuario) {
        EstatisticasUsuario estatisticasSalvas = estatisticasUsuarioDAO.salvarEstatisticas(estatisticasUsuario);
        return new ResponseEntity<>(estatisticasSalvas, HttpStatus.CREATED); // Retorna 201 ao salvar
    }

    // Endpoint para buscar estatísticas pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<EstatisticasUsuario> buscarEstatisticasPorId(@PathVariable Long id) {
        Optional<EstatisticasUsuario> estatisticasUsuario = estatisticasUsuarioDAO.buscarPorId(id);
        return estatisticasUsuario.map(ResponseEntity::ok) // Retorna 200 se encontrado
                .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna 404 se não encontrado
    }

    // Endpoint para listar todas as estatísticas
    @GetMapping
    public ResponseEntity<List<EstatisticasUsuario>> listarTodasEstatisticas() {
        List<EstatisticasUsuario> estatisticas = estatisticasUsuarioDAO.listarTodas();
        return ResponseEntity.ok(estatisticas); // Retorna 200 com a lista de estatísticas
    }

    // Endpoint para deletar estatísticas pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstatisticasPorId(@PathVariable Long id) {
        if (estatisticasUsuarioDAO.existsById(id)) {
            estatisticasUsuarioDAO.deletarPorId(id);
            return ResponseEntity.noContent().build(); // Retorna 204 ao deletar com sucesso
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
        }
    }

    // Endpoint para buscar estatísticas por usuário e categoria
    @GetMapping("/usuario/{usuarioId}/categoria/{categoriaId}")
    public ResponseEntity<List<EstatisticasUsuario>> buscarPorUsuarioECategoria(
            @PathVariable Long usuarioId, 
            @PathVariable Long categoriaId) {
        List<EstatisticasUsuario> estatisticas = estatisticasUsuarioDAO.buscarPorUsuarioECategoria(usuarioId, categoriaId);
        if (estatisticas.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se nenhuma estatística for encontrada
        } else {
            return ResponseEntity.ok(estatisticas); // Retorna 200 com a lista encontrada
        }
    }
}

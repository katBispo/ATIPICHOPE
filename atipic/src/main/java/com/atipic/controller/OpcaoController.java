package com.atipic.controller;

import com.atipic.model.Opcao;
import com.atipic.repository.OpcaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/opcoes")
public class OpcaoController {

    @Autowired
    private OpcaoDAO opcaoDAO;

    // Endpoint para salvar ou atualizar uma opção
    @PostMapping
    public ResponseEntity<Opcao> salvarOpcao(@RequestBody Opcao opcao) {
        Opcao opcaoSalva = opcaoDAO.salvarOpcao(opcao);
        return new ResponseEntity<>(opcaoSalva, HttpStatus.CREATED); // Retorna status 201 (Criado)
    }

    // Endpoint para deletar uma opção pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOpcao(@PathVariable Long id) {
        Optional<Opcao> opcao = opcaoDAO.buscarOpcaoPorId(id);
        if (opcao.isPresent()) {
            opcaoDAO.deletarOpcao(id);
            return ResponseEntity.noContent().build(); // Retorna status 204 (Sem conteúdo)
        } else {
            return ResponseEntity.notFound().build(); // Retorna status 404 (Não encontrado)
        }
    }

    // Endpoint para buscar uma opção pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Opcao> buscarOpcaoPorId(@PathVariable Long id) {
        Optional<Opcao> opcao = opcaoDAO.buscarOpcaoPorId(id);
        return opcao.map(ResponseEntity::ok) // Retorna status 200 (OK) se a opção for encontrada
                .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna status 404 se não encontrado
    }

    // Endpoint para listar todas as opções
    @GetMapping
    public ResponseEntity<List<Opcao>> listarTodasOpcoes() {
        List<Opcao> opcoes = opcaoDAO.listarTodasOpcoes();
        return ResponseEntity.ok(opcoes); // Retorna status 200 (OK) com a lista de opções
    }

    // Endpoint para listar opções vinculadas a uma pergunta específica
    @GetMapping("/pergunta/{perguntaId}")
    public ResponseEntity<List<Opcao>> listarOpcoesPorPerguntaId(@PathVariable Long perguntaId) {
        List<Opcao> opcoes = opcaoDAO.listarOpcoesPorPerguntaId(perguntaId);
        if (!opcoes.isEmpty()) {
            return ResponseEntity.ok(opcoes); // Retorna status 200 (OK) com a lista de opções
        } else {
            return ResponseEntity.notFound().build(); // Retorna status 404 se nenhuma opção for encontrada
        }
    }
}

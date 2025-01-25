package com.atipic.controller;

import com.atipic.model.Pergunta;
import com.atipic.repository.PerguntaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    @Autowired
    private PerguntaDAO perguntaDAO;

    // Endpoint para salvar ou atualizar uma pergunta
    @PostMapping
    public ResponseEntity<Pergunta> salvarPergunta(@RequestBody Pergunta pergunta) {
        Pergunta perguntaSalva = perguntaDAO.salvarPergunta(pergunta);
        return new ResponseEntity<>(perguntaSalva, HttpStatus.CREATED); // Retorna o progresso salvo com status 201
    }

    // Endpoint para deletar uma pergunta pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPergunta(@PathVariable Long id) {
        Optional<Pergunta> perguntaExistente = perguntaDAO.buscarPerguntaPorId(id);
        if (perguntaExistente.isPresent()) {
            perguntaDAO.deletarPergunta(id);
            return ResponseEntity.noContent().build(); // Retorna status 204 (sem conteúdo)
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se a pergunta não for encontrada
        }
    }

    // Endpoint para buscar uma pergunta pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Pergunta> buscarPerguntaPorId(@PathVariable Long id) {
        Optional<Pergunta> pergunta = perguntaDAO.buscarPerguntaPorId(id);
        return pergunta.map(ResponseEntity::ok) // Retorna a pergunta se encontrada
                        .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna 404 se não encontrada
    }

    // Endpoint para listar todas as perguntas
    @GetMapping
    public ResponseEntity<List<Pergunta>> listarTodasPerguntas() {
        List<Pergunta> perguntas = perguntaDAO.listarTodasPerguntas();
        return ResponseEntity.ok(perguntas); // Retorna a lista de perguntas
    }
}

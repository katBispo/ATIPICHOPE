package com.atipic.controller;

import com.atipic.model.Progresso;
import com.atipic.repository.ProgressoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/progresso")
public class ProgressoController {

    @Autowired
    private ProgressoDAO progressoDAO;

    // Endpoint para salvar ou atualizar o progresso
    @PostMapping
    public ResponseEntity<Progresso> salvarProgresso(@RequestBody Progresso progresso) {
        Progresso progressoSalvo = progressoDAO.salvarProgresso(progresso);
        return new ResponseEntity<>(progressoSalvo, HttpStatus.CREATED); // Retorna o progresso salvo com status 201
    }

    // Endpoint para deletar o progresso pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProgresso(@PathVariable Long id) {
        Optional<Progresso> progresso = progressoDAO.buscarProgressoPorId(id);
        if (progresso.isPresent()) {
            progressoDAO.deletarProgresso(id);
            return ResponseEntity.noContent().build(); // Retorna status 204 (sem conteúdo)
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se o progresso não for encontrado
        }
    }

    // Endpoint para buscar progresso pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Progresso> buscarProgressoPorId(@PathVariable Long id) {
        Optional<Progresso> progresso = progressoDAO.buscarProgressoPorId(id);
        return progresso.map(ResponseEntity::ok) // Retorna o progresso se encontrado
                        .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna 404 se não encontrado
    }

    // Endpoint para listar todos os progressos
    @GetMapping
    public ResponseEntity<List<Progresso>> listarTodosProgressos() {
        List<Progresso> progressos = progressoDAO.listarTodos();
        return ResponseEntity.ok(progressos); // Retorna a lista de progressos
    }
}

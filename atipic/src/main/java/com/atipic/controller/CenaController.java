package com.atipic.controller;

import com.atipic.model.Cena;
import com.atipic.repository.CenaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cenas")
public class CenaController {

    @Autowired
    private CenaDAO cenaDAO;

    // Endpoint para salvar uma nova cena ou atualizar uma existente
    @PostMapping
    public ResponseEntity<Cena> salvarCena(@RequestBody Cena cena) {
        Cena cenaSalva = cenaDAO.salvarCena(cena);
        return new ResponseEntity<>(cenaSalva, HttpStatus.CREATED); // Retorna a cena salva com status 201
    }

    // Endpoint para deletar uma cena pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCena(@PathVariable Long id) {
        cenaDAO.deletarCena(id);
        return ResponseEntity.noContent().build(); // Retorna status 204 (sem conteúdo)
    }

    // Endpoint para buscar uma cena pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Cena> buscarCenaPorId(@PathVariable Long id) {
        Optional<Cena> cena = cenaDAO.buscarCenaPorId(id);
        return cena.map(ResponseEntity::ok) // Retorna a cena se encontrada
                   .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna 404 se não encontrada
    }

    // Endpoint para listar todas as cenas
    @GetMapping
    public ResponseEntity<List<Cena>> listarTodasCenas() {
        List<Cena> cenas = cenaDAO.listarTodos();
        return ResponseEntity.ok(cenas); // Retorna a lista de cenas
    }
}

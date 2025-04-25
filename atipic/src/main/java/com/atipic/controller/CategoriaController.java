package com.atipic.controller;

import com.atipic.model.Categoria;
import com.atipic.repository.CategoriaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaDAO categoriaDAO;

    // Endpoint para salvar ou atualizar uma categoria
    @PostMapping
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaDAO.salvarCategoria(categoria);
        return new ResponseEntity<>(categoriaSalva, HttpStatus.CREATED);
    }

    // Endpoint para buscar uma categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaDAO.buscarCategoriaPorId(id);
        return categoria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodasCategorias() {
        List<Categoria> categorias = categoriaDAO.listarTodasCategorias();
        return ResponseEntity.ok(categorias);
    }

    // Endpoint para deletar uma categoria por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        if (categoriaDAO.existsById(id)) {
            categoriaDAO.deletarCategoria(id);
            return ResponseEntity.noContent().build(); // Status 204 (sem conteúdo)
        } else {
            return ResponseEntity.notFound().build(); // Status 404 (não encontrado)
        }
    }

    // Endpoint para buscar categorias por nome
    @GetMapping("/buscar")
    public ResponseEntity<List<Categoria>> buscarCategoriasPorNome(@RequestParam String nome) {
        List<Categoria> categorias = categoriaDAO.buscarCategoriasPorNome(nome);
        if (!categorias.isEmpty()) {
            return ResponseEntity.ok(categorias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

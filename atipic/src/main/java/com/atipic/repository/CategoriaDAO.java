package com.atipic.repository;

import com.atipic.model.Categoria;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CategoriaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Salvar ou atualizar uma categoria
    public Categoria salvarCategoria(Categoria categoria) {
        if (categoria.getId() == null) {
            entityManager.persist(categoria); // Insere uma nova categoria
        } else {
            categoria = entityManager.merge(categoria); // Atualiza a categoria existente
        }
        return categoria;
    }

    // Buscar uma categoria por ID
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        Categoria categoria = entityManager.find(Categoria.class, id);
        return Optional.ofNullable(categoria); // Retorna um Optional com a categoria encontrada ou vazio
    }

    // Listar todas as categorias
    public List<Categoria> listarTodasCategorias() {
        String jpql = "SELECT c FROM Categoria c";
        return entityManager.createQuery(jpql, Categoria.class).getResultList();
    }

    // Deletar uma categoria pelo ID
    public void deletarCategoria(Long id) {
        Categoria categoria = entityManager.find(Categoria.class, id);
        if (categoria != null) {
            entityManager.remove(categoria); // Remove a categoria se encontrada
        }
    }

    // Verificar se uma categoria existe pelo ID
    public boolean existsById(Long id) {
        String jpql = "SELECT COUNT(c) > 0 FROM Categoria c WHERE c.id = :id";
        return entityManager.createQuery(jpql, Boolean.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    // Buscar categorias pelo nome
    public List<Categoria> buscarCategoriasPorNome(String nome) {
        String jpql = "SELECT c FROM Categoria c WHERE c.nome LIKE :nome";
        return entityManager.createQuery(jpql, Categoria.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}

package com.atipic.repository;

import com.atipic.model.Pergunta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PerguntaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Método para salvar ou atualizar uma pergunta
    public Pergunta salvarPergunta(Pergunta pergunta) {
        if (pergunta.getId() == null) {
            entityManager.persist(pergunta); // Inserir nova pergunta
        } else {
            pergunta = entityManager.merge(pergunta); // Atualizar pergunta existente
        }
        return pergunta;
    }

    // Método para deletar uma pergunta pelo ID
    public void deletarPergunta(Long id) {
        Pergunta pergunta = entityManager.find(Pergunta.class, id); // Buscar pergunta pelo ID
        if (pergunta != null) {
            entityManager.remove(pergunta); // Remover pergunta do banco
        }
    }

    // Método para buscar uma pergunta pelo ID
    public Optional<Pergunta> buscarPerguntaPorId(Long id) {
        Pergunta pergunta = entityManager.find(Pergunta.class, id);
        return Optional.ofNullable(pergunta); // Retorna a pergunta ou Optional vazio
    }

    // Método para listar todas as perguntas
    public List<Pergunta> listarTodasPerguntas() {
        return entityManager.createQuery("SELECT p FROM Pergunta p", Pergunta.class).getResultList(); // Buscar todas as perguntas
    }
}

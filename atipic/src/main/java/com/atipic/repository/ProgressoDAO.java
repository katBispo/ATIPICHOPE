package com.atipic.repository;

import com.atipic.model.Progresso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressoDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    // Salvar ou atualizar um progresso
    public Progresso salvarProgresso(Progresso progresso) {
        if (progresso.getId() == null) {
            entityManager.persist(progresso); // Inserir novo progresso
        } else {
            progresso = entityManager.merge(progresso); // Atualizar progresso existente
        }
        return progresso;
    }

    // Deletar um progresso
    public void deletarProgresso(Long progressoId) {
        Progresso progresso = entityManager.find(Progresso.class, progressoId); // Buscar o progresso pelo ID
        if (progresso != null) {
            entityManager.remove(progresso); // Remover o progresso do banco
        }
    }

    // Buscar um progresso pelo seu ID
    public Optional<Progresso> buscarProgressoPorId(Long id) {
        Progresso progresso = entityManager.find(Progresso.class, id);
        return Optional.ofNullable(progresso); // Retorna o progresso ou um Optional vazio
    }

    // Listar todos os progressos
    public List<Progresso> listarTodos() {
        return entityManager.createQuery("SELECT p FROM Progresso p", Progresso.class).getResultList(); // Buscar todos os progressos
    }
}

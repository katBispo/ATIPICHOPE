package com.atipic.repository;

import com.atipic.model.Opcao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpcaoDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    // Salvar ou atualizar uma opção
    public Opcao salvarOpcao(Opcao opcao) {
        if (opcao.getId() == null) {
            entityManager.persist(opcao); // Inserir nova opção
        } else {
            opcao = entityManager.merge(opcao); // Atualizar opção existente
        }
        return opcao;
    }

    // Deletar uma opção pelo ID
    public void deletarOpcao(Long opcaoId) {
        Opcao opcao = entityManager.find(Opcao.class, opcaoId); // Buscar a opção pelo ID
        if (opcao != null) {
            entityManager.remove(opcao); // Remover a opção do banco
        }
    }

    // Buscar uma opção pelo ID
    public Optional<Opcao> buscarOpcaoPorId(Long id) {
        Opcao opcao = entityManager.find(Opcao.class, id);
        return Optional.ofNullable(opcao); // Retorna a opção ou um Optional vazio
    }

    // Listar todas as opções
    public List<Opcao> listarTodasOpcoes() {
        return entityManager.createQuery("SELECT o FROM Opcao o", Opcao.class).getResultList(); // Buscar todas as opções
    }

    // Listar todas as opções relacionadas a uma pergunta
    public List<Opcao> listarOpcoesPorPerguntaId(Long perguntaId) {
        return entityManager.createQuery(
                "SELECT o FROM Opcao o WHERE o.pergunta.id = :perguntaId", Opcao.class)
                .setParameter("perguntaId", perguntaId)
                .getResultList(); // Buscar opções filtrando pelo ID da pergunta
    }
}

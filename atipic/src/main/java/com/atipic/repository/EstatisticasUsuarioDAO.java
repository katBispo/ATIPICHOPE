package com.atipic.repository;

import com.atipic.model.EstatisticasUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EstatisticasUsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Salvar ou atualizar estatísticas do usuário
    @Transactional
    public EstatisticasUsuario salvarEstatisticas(EstatisticasUsuario estatisticasUsuario) {
        if (estatisticasUsuario.getId() == null) {
            entityManager.persist(estatisticasUsuario);
            return estatisticasUsuario;
        } else {
            return entityManager.merge(estatisticasUsuario);
        }
    }

    // Buscar estatísticas pelo ID
    public Optional<EstatisticasUsuario> buscarPorId(Long id) {
        EstatisticasUsuario estatisticasUsuario = entityManager.find(EstatisticasUsuario.class, id);
        return Optional.ofNullable(estatisticasUsuario);
    }

    // Listar todas as estatísticas
    public List<EstatisticasUsuario> listarTodas() {
        return entityManager.createQuery("SELECT e FROM EstatisticasUsuario e", EstatisticasUsuario.class)
                .getResultList();
    }

    // Deletar estatísticas pelo ID
    @Transactional
    public void deletarPorId(Long id) {
        EstatisticasUsuario estatisticasUsuario = entityManager.find(EstatisticasUsuario.class, id);
        if (estatisticasUsuario != null) {
            entityManager.remove(estatisticasUsuario);
        }
    }

    // Verificar se uma estatística existe pelo ID
    public boolean existsById(Long id) {
        return entityManager.find(EstatisticasUsuario.class, id) != null;
    }

    // Buscar estatísticas de um usuário específico por categoria
    public List<EstatisticasUsuario> buscarPorUsuarioECategoria(Long usuarioId, Long categoriaId) {
        return entityManager.createQuery(
                        "SELECT e FROM EstatisticasUsuario e WHERE e.usuario.id = :usuarioId AND e.categoria.id = :categoriaId",
                        EstatisticasUsuario.class)
                .setParameter("usuarioId", usuarioId)
                .setParameter("categoriaId", categoriaId)
                .getResultList();
    }
}

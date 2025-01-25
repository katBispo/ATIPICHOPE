package com.atipic.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atipic.model.Cena;
import com.atipic.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Service
public class CenaDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    // Salvar uma cena no banco
    public Cena salvarCena(Cena cena) {
        if (cena.getId() == null) {
            entityManager.persist(cena); // Inserir nova cena
        } else {
            cena = entityManager.merge(cena); // Atualizar cena existente
        }
        return cena;
    }

    // Deletar uma cena, supondo que ela esteja relacionada com o Usuário
    public void deletarCena(Long cenaId) {
        Cena cena = entityManager.find(Cena.class, cenaId); // Buscar a cena pelo ID
        if (cena != null) {
            entityManager.remove(cena); // Remover a cena do banco
        }
    }

    // Buscar uma cena pelo seu ID
    public Optional<Cena> buscarCenaPorId(Long id) {
        Cena cena = entityManager.find(Cena.class, id);
        return Optional.ofNullable(cena); // Retornar a cena ou um Optional vazio
    }

    // Listar todas as cenas
    public List<Cena> listarTodos() {
        return entityManager.createQuery("SELECT c FROM Cena c", Cena.class).getResultList(); // Buscar todas as cenas
    }
}

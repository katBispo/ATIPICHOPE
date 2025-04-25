package com.atipic.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @ManyToMany(mappedBy = "categorias")
    @JsonBackReference
    private List<Cena> cenas;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Progresso> progresso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Cena> getCenas() {
        return cenas;
    }

    public void setCenas(List<Cena> cenas) {
        this.cenas = cenas;
    }

    public List<Progresso> getProgresso() {
        return progresso;
    }

    public void setProgresso(List<Progresso> progresso) {
        this.progresso = progresso;
    }

    
}

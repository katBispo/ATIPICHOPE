package com.atipic.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "perguntas")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String textoPergunta;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Opcao> opcoes;

    @Column(nullable = false)
    private Long idOpcaoCorreta;

    @ManyToOne
    @JoinColumn(name = "cena_id", nullable = false)
    @JsonBackReference
    private Cena cena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextoPergunta() {
        return textoPergunta;
    }

    public void setTextoPergunta(String textoPergunta) {
        this.textoPergunta = textoPergunta;
    }

    public List<Opcao> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes = opcoes;
    }

    public Long getIdOpcaoCorreta() {
        return idOpcaoCorreta;
    }

    public void setIdOpcaoCorreta(Long idOpcaoCorreta) {
        this.idOpcaoCorreta = idOpcaoCorreta;
    }

    public Cena getCena() {
        return cena;
    }

    public void setCena(Cena cena) {
        this.cena = cena;
    }

    
}

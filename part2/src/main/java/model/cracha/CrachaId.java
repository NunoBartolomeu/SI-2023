package model.cracha;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import model.jogo.Jogo;

@Embeddable
public class CrachaId {
    private String nome;
    @Column(name = "id_jogo")
    private Jogo jogo;

    public CrachaId() { }

    public String getNome() { return this.nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Jogo getJogo() { return this.jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }

}

package model.compra;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import model.jogador.Jogador;
import model.jogo.Jogo;

@Embeddable
public class CompraId {
    @Column(name = "id_jogador")
    private Jogador jogador;
    @Column(name = "id_jogo")
    private Jogo jogo;

    public CompraId() { }

    public Jogador getJogador() { return this.jogador; }

    public void setJogador(Jogador jogador) { this.jogador = jogador; }

    public Jogo getJogo() { return this.jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }
}

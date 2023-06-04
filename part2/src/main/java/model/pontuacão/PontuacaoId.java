package model.pontuacão;

import jakarta.persistence.*;

@Embeddable
public class PontuacaoId {

    @Column(name = "id_partida", insertable = true, updatable = false)
    private int idPartida;

    @Column(name = "id_jogo", insertable = true, updatable = false)
    private String idJogo;

    @Column(name = "id_jogador", insertable = true, updatable = false)
    private int idJogador;

    public PontuacaoId() { }

    public long getIdPartida() { return this.idPartida; }

    public void setIdPartida(int idPartida) { this.idPartida = idPartida; }

    public String getIdJogo() { return this.idJogo; }

    public void setIdJogo(String idJogo) { this.idJogo = idJogo; }

    public int getIdJogador() { return this.idJogador; }

    public void setIdJogador(int idJogador) { this.idJogador = idJogador; }
}

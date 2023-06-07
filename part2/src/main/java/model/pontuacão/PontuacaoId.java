package model.pontuacão;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class PontuacaoId implements Serializable {

    private long idPartida;

    private String idJogo;

    private int idJogador;

    public PontuacaoId() { }

    public long getIdPartida() { return this.idPartida; }

    public void setIdPartida(long idPartida) { this.idPartida = idPartida; }

    public String getIdJogo() { return this.idJogo; }

    public void setIdJogo(String idJogo) { this.idJogo = idJogo; }

    public int getIdJogador() { return this.idJogador; }

    public void setIdJogador(int idJogador) { this.idJogador = idJogador; }
}

package model.compra;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CompraId implements Serializable {
    private int idJogador;
    private String idJogo;

    public CompraId() { }

    public int getIdJogador() { return this.idJogador; }

    public void setIdJogador(int idJogador) { this.idJogador = idJogador; }

    public String getIdJogo() { return this.idJogo; }

    public void setIdJogo(String idJogo) { this.idJogo = idJogo; }
}

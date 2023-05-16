package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the compras database table.
 * 
 */
@Entity(name = "compras")
@Table(name = "compras")
@NamedQuery(name = "compras.findAll", query = "SELECT c FROM compras c")
public class Compra implements Serializable {
    @EmbeddedId
    private CompraId id;

    private String url;

    private int limite_pontos;

    public Compra() { }

    public CompraId getId() { return this.id; }

    public void setId(CompraId id) { this.id = id; }

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    public int getLimite_pontos() { return this.limite_pontos; }

    public void setLimite_pontos(int limite_pontos) { this.limite_pontos = limite_pontos; }
}

@Embeddable
class CompraId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    public CompraId() { }

    public Jogo getJogo() { return this.jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public Jogador getJogador() { return this.jogador; }

    public void setJogador(Jogador jogador) { this.jogador = jogador; }
}
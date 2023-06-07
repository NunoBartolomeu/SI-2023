package model.compra;

import jakarta.persistence.*;
import model.jogador.Jogador;
import model.jogo.Jogo;
import org.eclipse.persistence.annotations.CompositeMember;

import java.util.Date;

@Entity(name = "compras")
@Table(name = "compras")
public class Compra {
    @EmbeddedId
    private CompraId id;

    @ManyToOne
    @MapsId("idJogador")
    @JoinColumn(name = "id_jogador")
    private Jogador jogador;

    @ManyToOne
    @MapsId("idJogo")
    @JoinColumn(name = "id_jogo")
    private Jogo jogo;

    @Column(name = "data_de_compra")
    private Date dataDeCompra;

    private Float preco;

    public Compra() { }

    public CompraId getId() { return this.id; }

    public void setId(CompraId id) { this.id = id; }

    public Jogador getJogador() { return jogador; }

    public void setJogador(Jogador jogador) { this.jogador = jogador; }

    public Jogo getJogo() { return jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public Date getDataDeCompra() { return this.dataDeCompra; }

    public void setDataDeCompra(Date dataDeCompra) { this.dataDeCompra = dataDeCompra; }

    public Float getPreco() { return this.preco; }

    public void setPreco(Float preco) { this.preco = preco; }
}

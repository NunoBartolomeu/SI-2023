package model.jogo;

import jakarta.persistence.*;
import model.compra.Compra;
import model.estatisticas_jogo.EstatisticasJogo;
import model.partida.Partida;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity(name = "jogos")
@Table(name = "jogos")
public class Jogo {
    @Id
    private String id;

    private String nome;

    private String url;

    @OneToOne(mappedBy = "jogo")
    private EstatisticasJogo estatisticas;

    @OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL)
    private Set<Compra> compras;

    @OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL)
    private Set<Partida> partidas;

    public Jogo() { }

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public String getNome() { return this.nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    public EstatisticasJogo getEstatisticas() { return this.estatisticas; }

    public void setEstatisticas(EstatisticasJogo estatisticas) { this.estatisticas = estatisticas; }

    public Set<Compra> getCompras() { return compras; }

    public void setCompras(Set<Compra> compras) { this.compras = compras; }

    public Compra addCompra(Compra compra){
        getCompras().add(compra);
        compra.setJogo(this);
        return compra;
    }

    public Set<Partida> getPartidas() { return partidas; }

    public void setPartidas(Set<Partida> partidas) { this.partidas = partidas; }

}

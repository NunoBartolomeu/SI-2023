package model.cracha;

import jakarta.persistence.*;
import model.jogador.Jogador;
import model.jogo.Jogo;

import java.util.Set;

@Table(name = "crachas")
@Entity(name = "crachas")
public class Cracha {

    @EmbeddedId
    private CrachaId id;

    private String url;

    @Column(name = "limite_pontos")
    private int limitePontos;

    @ManyToMany(mappedBy="crachas", cascade= CascadeType.REMOVE)
    private Set<Jogador> jogadores;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idJogo")
    @JoinColumn(name = "id_jogo")
    private Jogo jogo;

    public Cracha() { }

    public CrachaId getId() { return this.id; }

    public void setId(CrachaId id) { this.id = id; }

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    public int getLimitePontos() { return this.limitePontos; }

    public void setLimitePontos(int limitePontos) { this.limitePontos = limitePontos; }

    public Jogo getJogo() { return jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public Set<Jogador> getJogadores() { return this.jogadores; }

    public void setJogadores(Set<Jogador> jogadores) { this.jogadores = jogadores; }

}

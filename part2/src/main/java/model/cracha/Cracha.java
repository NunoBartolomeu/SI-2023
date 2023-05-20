package model.cracha;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToMany;
import model.jogador.Jogador;
import java.util.Set;

public class Cracha {

    @EmbeddedId
    private CrachaId id;

    private String url;

    private int limitePontos;

    @ManyToMany(mappedBy="jogadores",cascade= CascadeType.REMOVE)
    private Set<Jogador> jogadores;

    public Cracha() { }

    public CrachaId getId() { return this.id; }

    public void setId(CrachaId id) { this.id = id; }

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    public int getLimitePontos() { return this.limitePontos; }

    public void setLimitePontos(int limitePontos) { this.limitePontos = limitePontos; }

    public Set<Jogador> getJogadores() { return this.jogadores; }

    public void setJogadores(Set<Jogador> jogadores) { this.jogadores = jogadores; }

}

package model.partida_multijogador;

import jakarta.persistence.*;
import model.partida.Partida;
import model.pontuacao.Pontuacao_Multi_Jogador;

import java.util.Set;

@Entity(name = "partidas_multi_jogador")
@Table(name = "partidas_multi_jogador")
public class PartidaMultijogador {
    @EmbeddedId
    private PartidaMultijogadorId id;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "id", referencedColumnName = "id"),
            @JoinColumn(name = "id_jogo", referencedColumnName = "id_jogo")
    })
    private Partida partida;

    @OneToMany(mappedBy= "partida", cascade=CascadeType.PERSIST, orphanRemoval=true)
    private Set<Pontuacao_Multi_Jogador> pontuacoes;

    private String estado;

    public PartidaMultijogador() { }

    public PartidaMultijogadorId getId() { return this.id; }

    public void setId(PartidaMultijogadorId id) { this.id = id; }

    public String getEstado(){ return this.estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Set<Pontuacao_Multi_Jogador> getPontuacoes() { return pontuacoes; }

    public void setPontuacoes(Set<Pontuacao_Multi_Jogador> pontuacoes) { this.pontuacoes = pontuacoes; }

    public Partida getPartida() { return partida; }

    public void setPartida(Partida partida) { this.partida = partida; }
}


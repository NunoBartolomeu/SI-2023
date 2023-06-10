package model.partida_multijogador;

import jakarta.persistence.*;
import model.jogo.Jogo;
import model.partida.Partida;
import model.partida.PartidaId;
import model.partida_normal.PartidaNormal;
import model.pontuacão.Pontuacao_Multi_Jogador;

import java.util.Set;

@Entity(name = "partidas_multi_jogador")
@Table(name = "partidas_multi_jogador")
public class PartidaMultijogador {
/*
    @EmbeddedId
    private PartidaId id;

 */
    @EmbeddedId
    private PartidaMultijogadorId id;
/*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "id_jogo", referencedColumnName = "id_jogo", insertable = false, updatable = false)
    })
    private Partida partida;

 */

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "id", referencedColumnName = "id"),
            @JoinColumn(name = "id_jogo", referencedColumnName = "id_jogo")
    })
    private Partida partida;
/*
    @ManyToOne
    @JoinColumn(name = "id_jogo", referencedColumnName = "id")
    private Jogo jogo;
 */

    @OneToMany(mappedBy= "partida", cascade=CascadeType.PERSIST, orphanRemoval=true)
    private Set<Pontuacao_Multi_Jogador> pontuacoes;

    private String estado;

    public PartidaMultijogador() { }
/*
    public PartidaId getId() { return this.id; }

    public void setId(PartidaId id) { this.id = id; }

 */
    public PartidaMultijogadorId getId() { return this.id; }

    public void setId(PartidaMultijogadorId id) { this.id = id; }

    public String getEstado(){ return this.estado; }

    public void setEstado(String estado) { this.estado = estado; }
/*
    public Jogo getJogo() { return jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }

 */

    public Set<Pontuacao_Multi_Jogador> getPontuacoes() { return pontuacoes; }

    public void setPontuacoes(Set<Pontuacao_Multi_Jogador> pontuacoes) { this.pontuacoes = pontuacoes; }

    public Partida getPartida() { return partida; }

    public void setPartida(Partida partida) { this.partida = partida; }
}


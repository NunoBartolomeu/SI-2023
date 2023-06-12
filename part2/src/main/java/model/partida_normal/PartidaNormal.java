package model.partida_normal;

import jakarta.persistence.*;
import model.jogador.Jogador;
import model.partida.Partida;

@Entity(name = "partidas_normais")
public class PartidaNormal {

    @EmbeddedId
    private PartidaNormalId id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "id_jogo", referencedColumnName = "id_jogo", insertable = false, updatable = false)
    })
    private Partida partida;

    @ManyToOne
    @JoinColumn(name = "id_jogador", referencedColumnName = "id")
    private Jogador jogador;

    public PartidaNormal() { }

    private int dificuldade;

    private int pontos;

    public PartidaNormalId getId() { return id; }

    public void setId(PartidaNormalId id) { this.id = id; }

    public int getPontos() { return pontos; }

    public void setPontos(int pontos) { this.pontos = pontos; }

    public Jogador getJogador() { return jogador; }

    public void setJogador(Jogador jogador) { this.jogador = jogador; }

    public int getDificuldade() { return this.dificuldade; }

    public void setDificuldade(int dificuldade) { this.dificuldade = dificuldade; }

    public Partida getPartida() { return partida; }

    public void setPartida(Partida partida) { this.partida = partida; }

}

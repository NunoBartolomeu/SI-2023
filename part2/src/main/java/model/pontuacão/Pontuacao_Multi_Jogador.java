package model.pontuacão;

import jakarta.persistence.*;
import model.jogador.Jogador;
import model.jogo.Jogo;
import model.partida.Partida;
import model.partida_multijogador.PartidaMultijogador;

import java.io.Serializable;


@Entity(name = "pontuacoes_multi_jogador")
@Table(name = "pontuacoes_multi_jogador")
public class Pontuacao_Multi_Jogador implements Serializable {

    @EmbeddedId
    private PontuacaoId id;

    public Pontuacao_Multi_Jogador() { }

    @JoinColumns({
            @JoinColumn(name = "id_partida", referencedColumnName = "id"),
            @JoinColumn(name = "id_jogo", referencedColumnName = "id_jogo")
    })
    @ManyToOne
    private PartidaMultijogador partida;


    @ManyToOne
    @MapsId("id_jogador")
    @JoinColumn(name = "id_jogador")
    private Jogador jogador;

    private int pontos;
/*
    public PartidaMultijogador getPartida() { return partida; }

    public void setPartida(PartidaMultijogador partida) { this.partida = partida; }

 */

    public PontuacaoId getId(){ return this.id; }

    public void setId(PontuacaoId id) { this.id = id; }

    public int getPontos() { return this.pontos; }

    public void setPontos(int pontos) { this.pontos = pontos; }
}


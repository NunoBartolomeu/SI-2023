package model.pontuacão;

import jakarta.persistence.*;
import model.jogador.Jogador;
import model.jogo.Jogo;
import model.partida.Partida;
import model.partida.PartidaId;


@Entity(name = "pontuacoes")
@Table(name = "pontuacoes")
public class Pontuacao {
    @EmbeddedId
    private PontuacaoId id;

    public Pontuacao() { }
/*
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id", referencedColumnName = "id"),
            @JoinColumn(name = "id_jogo", referencedColumnName = "id_jogo")
    })
    private Partida partida;

 */
    @ManyToOne
    @MapsId("idJogador")
    @JoinColumn(name = "id_jogador")
    private Jogador jogador;

    @ManyToOne
    @MapsId("idJogo")
    @JoinColumn(name = "id_jogo")
    private Jogo jogo;

    private int pontos;

    public PontuacaoId getId(){ return this.id; }

    public void setId(PontuacaoId id) { this.id = id; }

    public int getPontos() { return this.pontos; }

    public void setPontos(int pontos) { this.pontos = pontos; }
}

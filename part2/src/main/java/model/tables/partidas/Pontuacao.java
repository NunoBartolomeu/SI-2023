package model.partidas;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the pontuacoes database table.
 * 
 */
@Entity(name = "pontuacoes")
@Table(name = "pontuacoes")
@NamedQuery(name = "pontuacoes.findAll", query = "SELECT p FROM pontuacoes p")
public class Pontuacao implements Serializable {
    @EmbeddedId
    private PontuacaoId id;

    private String pontos;

    public Pontuacao() {
    }

    public PontuacaoId getId() {
        return this.id;
    }

    public void setId(PontuacaoId id) {
        this.id = id;
    }

    public String getPontos() {
        return this.pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

}

public class PontuacaoId implements Serializable {
    @Column(name = "id_jogador")
    private Integer id_jogador;

    @Column(name = "id_jogo")
    private Integer id_jogo;

    @Column(name = "id_partida")
    private Integer id_partida;

    public PontuacaoId() {
    }

    public Integer getId_jogador() {
        return this.id_jogador;
    }

    public void setId_jogador(Integer id_jogador) {
        this.id_jogador = id_jogador;
    }

    public Integer getId_jogo() {
        return this.id_jogo;
    }

    public void setId_jogo(Integer id_jogo) {
        this.id_jogo = id_jogo;
    }   

    public Integer getId_partida() {
        return this.id_partida;
    }

    public void setId_partida(Integer id_partida) {
        this.id_partida = id_partida;
    }

}
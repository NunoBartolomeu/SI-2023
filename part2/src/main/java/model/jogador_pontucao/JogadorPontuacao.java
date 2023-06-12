package model.jogador_pontucao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table
public class JogadorPontuacao {
    @Id
    @Column(name = "id_jogador")
    private int idJogador;

    private int pontos;

    public JogadorPontuacao(){}

    public int getIdJogador() { return idJogador; }

    public void setIdJogador(int idJogador) { this.idJogador = idJogador; }

    public int getPontos() { return pontos; }

    public void setPontos(int pontos) { this.pontos = pontos; }
}

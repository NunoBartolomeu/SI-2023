package model.user;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the estatisticas_jogador database table.
 * 
 */
@Entity(name = "estatisticas_jogador")
@Table(name = "estatisticas_jogador")
@NamedQuery(name = "estatisticas_jogador.findAll", query = "SELECT e FROM estatisticas_jogador e")
public class EstatisticaJogador implements Serializable {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador", referencedColumnName = "id")    
    private Integer id_jogador;

    private String num_jogos;

    private String num_partidas;

    private String total_pontos;

    public EstatisticaJogador() {
    }

    public print() {
        System.out.println("EstatisticaJogador: " + this.id_jogador + " " + this.num_jogos + " " + this.num_partidas + " " + this.total_pontos);
    }

    public Integer getId_jogador() {
        return this.id_jogador;
    }

    public void setId_jogador(Integer id_jogador) {
        this.id_jogador = id_jogador;
    }

    public String getNum_jogos() {
        return this.num_jogos;
    }

    public void setNum_jogos(String num_jogos) {
        this.num_jogos = num_jogos;
    }

    public String getNum_partidas() {
        return this.num_partidas;
    }

    public void setNum_partidas(String num_partidas) {
        this.num_partidas = num_partidas;
    }

    public String getTotal_pontos() {
        return this.total_pontos;
    }

    public void setTotal_pontos(String total_pontos) {
        this.total_pontos = total_pontos;
    }

}
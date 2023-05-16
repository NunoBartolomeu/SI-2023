package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the estatisticas_jogo database table.
 * 
 */
@Entity(name = "estatisticas_jogo")
@Table(name = "estatisticas_jogo")
@NamedQuery(name = "estatisticas_jogo.findAll", query = "SELECT e FROM estatisticas_jogo e")
public class EstatisticaJogo implements Serializable {
    @Id
    private String id;

    private int num_jogadores;

    private int num_partidas;

    private int total_pontos;

    public EstatisticaJogo() { }

    public String getId() { return this.id; }

    //Can't be longer than 10 characters
    public void setId(String id) { 
        if (id.length() <= 10)
            this.id = id;
        else 
            throw new Error("Id: " + id + "isn't valid!");
    }

    public int getNum_jogadores() { return this.num_jogadores; }

    public void setNum_jogadores(int num_jogadores) { this.num_jogadores = num_jogadores; }

    public int getNum_partidas() { return this.num_partidas; }

    public void setNum_partidas(int num_partidas) { this.num_partidas = num_partidas; }

    public int getTotal_pontos() { return this.total_pontos; }

    public void setTotal_pontos(int total_pontos) { this.total_pontos = total_pontos; }

}

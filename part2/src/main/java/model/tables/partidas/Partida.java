package model.partidas;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the partidas database table.
 * 
 */
@Entity(name = "partidas")
@Table(name = "partidas")
@NamedQuery(name = "partidas.findAll", query = "SELECT p FROM partidas p")
public class Partida implements Serializable {
    @EmbeddedId
    private PartidaId id;

    private String data;

    private String duracao;

    private String vencedor;

    public Partida() {
    }

    public PartidaId getId() {
        return this.id;
    }

    public void setId(PartidaId id) {
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDuracao() {
        return this.duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getVencedor() {
        return this.vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

}

public class PartidaId implements Serializable {
    @Column(name = "id_jogo")
    private Integer id_jogo;

    @Column(name = "id_partida")
    private Integer id_partida;

    public PartidaId() {
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
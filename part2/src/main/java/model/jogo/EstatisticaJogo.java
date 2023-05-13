package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the estatisticas_jogo database table.
 * 
 */
@Entity
@Table(name = "estatisticas_jogo")
@NamedQuery(name = "EstatisticaJogo.findAll", query = "SELECT e FROM EstatisticaJogo e")
public class EstatisticaJogo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int derrotas;

    private int empates;

    private int vitorias;

    // bi-directional one-to-one association to Jogo
    @OneToOne
    @JoinColumn(name = "id_jogo")
    private Jogo jogo;

    public EstatisticaJogo() {
    }

    public EstatisticaJogo(int derrotas, int empates, int vitorias, Jogo jogo) {
        this.derrotas = derrotas;
        this.empates = empates;
        this.vitorias = vitorias;
        this.jogo = jogo;
    }

    public EstatisticaJogo(int id, int derrotas, int empates, int vitorias, Jogo jogo) {
        this.id = id;
        this.derrotas = derrotas;
        this.empates = empates;
        this.vitorias = vitorias;
        this.jogo = jogo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDerrotas() {
        return this.derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return this.empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getVitorias() {
        return this.vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

}
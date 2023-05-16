package model.partidas;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the partidas database table.
 * 
 */
@Entity
@Table(name = "partidas")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p")
public abstract class Partida implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int pontuacao;

    // bi-directional many-to-one association to Pontuacao
    @ManyToOne
    @JoinColumn(name = "pontuacao_id")
    private Pontuacao pontuacaoBean;

    public Partida() {
    }

    public Partida(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getId() {
        return this.id;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Pontuacao getPontuacaoBean() {
        return this.pontuacaoBean;
    }

    public void setPontuacaoBean(Pontuacao pontuacaoBean) {
        this.pontuacaoBean = pontuacaoBean;
    }

}
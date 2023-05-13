package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the crachas_obtidos database table.
 * 
 */
@Entity
@Table(name = "crachas_obtidos")
@NamedQuery(name = "CrachaObtido.findAll", query = "SELECT c FROM CrachaObtido c")
public class CrachaObtido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cracha_obtido")
    private int idCrachaObtido;

    // bi-directional many-to-one association to Cracha
    @ManyToOne
    @JoinColumn(name = "id_cracha")
    private Cracha cracha;

    // bi-directional many-to-one association to Jogador
    @ManyToOne
    @JoinColumn(name = "id_jogador")
    private Jogador jogador;

    public CrachaObtido() {
    }

    public int getIdCrachaObtido() {
        return this.idCrachaObtido;
    }

    public void setIdCrachaObtido(int idCrachaObtido) {
        this.idCrachaObtido = idCrachaObtido;
    }

    public Cracha getCracha() {
        return this.cracha;
    }

    public void setCracha(Cracha cracha) {
        this.cracha = cracha;
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

}
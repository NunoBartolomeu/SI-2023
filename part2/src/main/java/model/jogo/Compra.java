package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the compras database table.
 * 
 */
@Entity
@Table(name = "compras")
@NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c")
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantidade;

    // bi-directional many-to-one association to Jogo
    @ManyToOne
    @JoinColumn(name = "id_jogo")
    private Jogo jogo;

    // bi-directional many-to-one association to Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Compra() {
    }

    public Compra(int quantidade, Jogo jogo, Usuario usuario) {
        this.quantidade = quantidade;
        this.jogo = jogo;
        this.usuario = usuario;
    }

    public Compra(int id, int quantidade, Jogo jogo, Usuario usuario) {
        this.id = id;
        this.quantidade = quantidade;
        this.jogo = jogo;
        this.usuario = usuario;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
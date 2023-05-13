package model.conversa;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the mensagens database table.
 * 
 */
@Entity
@Table(name = "mensagens")
@NamedQuery(name = "Mensagen.findAll", query = "SELECT m FROM Mensagen m")
public class Mensagen implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String conteudo;

    // bi-directional many-to-one association to Conversa
    @ManyToOne
    @JoinColumn(name = "conversa_id")
    private Conversa conversa;

    // bi-directional many-to-one association to Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Mensagen() {
    }

    public Mensagen(String conteudo, Conversa conversa, Usuario usuario) {
        this.conteudo = conteudo;
        this.conversa = conversa;
        this.usuario = usuario;
    }

    public Mensagen(int id, String conteudo, Conversa conversa, Usuario usuario) {
        this.id = id;
        this.conteudo = conteudo;
        this.conversa = conversa;
        this.usuario = usuario;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conversa getConversa() {
        return this.conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
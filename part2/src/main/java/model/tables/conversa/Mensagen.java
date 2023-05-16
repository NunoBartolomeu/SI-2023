package model.conversa;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the mensagens database table.
 * 
 */
@Entity(name = "mensagens")
@Table(name = "mensagens")
@NamedQuery(name = "mensagens.findAll", query = "SELECT m FROM mensagens m")
public class Mensagem implements Serializable {
    @EmbeddedId
    private MensagemId id;

    private Player jogador;

    private String texto;

    private Date data;

    public Mensagem() { }

    public MensagemId getId() { return this.id; }

    public void setId(MensagemId id) { this.id = id; }

    public Player getJogador() { return this.jogador; }

    public void setJogador(Player jogador) { this.jogador = jogador; }

    public String getTexto() { return this.texto; }

    public void setTexto(String texto) { this.texto = texto; }

    public Date getData() { return this.data; }

    public void setData(Date data) { this.data = data; }

}
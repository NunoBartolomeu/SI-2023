package model.mensagen;

import jakarta.persistence.*;
import model.conversa.Conversa;
import model.jogador.Jogador;

import java.util.Date;

@Entity(name = "mensagens")
@Table(name = "mensagens")
public class Mensagem {
    @EmbeddedId
    private MensagemId id;

    @JoinColumn(name = "id_jogador")
    private Jogador autor;

    @ManyToOne
    @MapsId("idConversa")
    @JoinColumn(name = "id_conversa")
    private Conversa conversa;

    private String texto;

    private Date data;

    public Mensagem() { }

    public MensagemId getId() { return this.id; }

    public void setId(MensagemId id) { this.id = id; }

    public Jogador getAutor() { return this.autor; }

    public void setAutor(Jogador autor) { this.autor = autor; }

    public Conversa getConversa() { return conversa; }

    public void setConversa(Conversa conversa) { this.conversa = conversa; }

    public String getTexto() { return this.texto; }

    public void setTexto(String texto) { this.texto = texto; }

    public Date getData() { return this.data; }

    public void setData(Date data) { this.data = data; }

}

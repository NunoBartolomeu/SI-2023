package model.mensagen;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import model.jogador.Jogador;

import java.util.Date;

@Entity(name = "mensagens")
@Table(name = "mensagens")
public class Mensagem {
    @EmbeddedId
    private MensagemID id;

    @Column(name = "id_jogador")
    private Jogador autor;

    private String texto;

    private Date data;

    public Mensagem() { }

    public MensagemID getId() { return this.id; }

    public void setId(MensagemID id) { this.id = id; }

    public Jogador getAutor() { return this.autor; }

    public void setAutor(Jogador autor) { this.autor = autor; }

    public String getTexto() { return this.texto; }

    public void setTexto(String texto) { this.texto = texto; }

    public Date getData() { return this.data; }

    public void setData(Date data) { this.data = data; }

}

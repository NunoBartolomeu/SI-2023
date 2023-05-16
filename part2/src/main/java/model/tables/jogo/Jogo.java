package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the jogos database table.
 * 
 */
@Entity(name = "jogos")
@Table(name = "jogos")
@NamedQuery(name = "jogos.findAll", query = "SELECT j FROM jogos j")
public class Jogo implements Serializable {
    @Id
    private String id;

    private String nome;

    private String url;

    public Jogo() { }

    public String getId() { return this.id; }

    //Can't be longer than 10 characters
    public void setId(String id) { 
        if (id.length() <= 10)
            this.id = id;
        else 
            throw new Error("Id: " + id + "isn't valid!");
    }

    public String getNome() { return this.nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

}
package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the jogos database table.
 * 
 */
@Entity
@Table(name = "jogos")
@NamedQuery(name = "Jogo.findAll", query = "SELECT j FROM Jogo j")
public class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String url;

    public Jogo() {
    }

    public Jogo(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    public Jogo(int id, String nome, String url) {
        this.id = id;
        this.nome = nome;
        this.url = url;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getUrl() {
        return this.url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
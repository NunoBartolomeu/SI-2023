package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the crachas database table.
 * 
 */
@Entity(name = "crachas")
@Table(name = "crachas")
@NamedQuery(name = "crachas.findAll", query = "SELECT c FROM crachas c")
public class Cracha implements Serializable {
    @EmbeddedId
    private CrachaId id;

    private String url;

    private int limite_pontos;

    @OneToMany(mappedBy = "cracha")
    private Jogador[] donos;

    public Cracha() { }

    public CrachaId getId() { return this.id; }

    public void setId(CrachaId id) { this.id = id; }

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    public int getLimite_pontos() { return this.limite_pontos; }

    public void setLimite_pontos(int limite_pontos) { this.limite_pontos = limite_pontos; }

}
package model.regiao;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the regioes database table.
 * 
 */
@Entity(name = "regioes")
@Table(name = "regioes")
public class Regiao implements Serializable {
    @Id
    private String nome;

    public Regiao() { }

    public String getNome() { return this.nome; }

    public void setNome(String nome) { this.nome = nome; }

}
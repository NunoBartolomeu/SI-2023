package model.conversa;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the conversas database table.
 * 
 */
@Entity(name = "conversas")
@Table(name = "conversas")
@NamedQuery(name = "conversas.findAll", query = "SELECT c FROM conversas c")
public class Conversa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    public Conversa() { }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getNome() { return this.nome; }

    public void setNome(String nome) { this.nome = nome; }

}
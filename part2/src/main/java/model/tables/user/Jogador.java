package model.tables.user;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.*;
import model.tables.Regiao;


/**
 * The persistent class for the jogadores database table.
 * 
 */
@Entity (name="jogadores")
@Table(name="jogadores")
@NamedQuery(name="jogadores.findAll", query="SELECT j FROM jogadores j")
public class Jogador implements Serializable {

    private static final List<String> estados = Arrays.asList("inativo", "ativo", "banido");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String email;

    private String estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regiao", referencedColumnName = "nome")
    private Regiao regiao;

    public Jogador() { }

    public void print() {
        System.out.println("Jogador: " + this.id + " " + this.username + " " + this.email + " " + this.estado + " " + this.regiao.getNome());
    }

    public Integer getId() { return this.id; }

    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public String getEstado() { return this.estado; }

    public void setEstado(String estado) {
        if (estados.contains(estado))
            this.estado = estado;
        else
            throw new Error("Estado: " + estado + "isn't valid!");
    }

    public Regiao getRegiao() { return this.regiao; }

    public void setRegiao(Regiao regiao) { this.regiao = regiao; }
    
}
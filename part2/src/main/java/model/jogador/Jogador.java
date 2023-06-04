package model.jogador;

import java.io.Serializable;
import java.util.*;

import jakarta.persistence.*;
import model.compra.Compra;
import model.conversa.Conversa;
import model.cracha.Cracha;
import model.estatisticas_jogador.EstatisticasJogador;
import model.partida.Partida;
import model.regiao.Regiao;
import org.glassfish.jaxb.core.v2.TODO;


/**
 * The persistent class for the jogadores database table.
 * 
 */
@Entity(name="jogadores")
@Table(name="jogadores")
public class Jogador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String email;

    private String estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regiao", referencedColumnName = "nome")
    private Regiao regiao;

    @ManyToMany(cascade=CascadeType.REMOVE)
    @JoinTable(name="amigos",
            joinColumns=@JoinColumn(name="id_jogador1"),
            inverseJoinColumns=@JoinColumn(name="id_jogador2"))
    private Set<Jogador> amigos;

    @OneToMany(mappedBy = "jogador")
    private Set<Compra> compras;

    @OneToOne(mappedBy = "jogador")
    private EstatisticasJogador estatisticas;

    @ManyToMany(mappedBy = "participantes")
    private Set<Conversa> conversas;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name="crachas_obtidos",
            joinColumns=@JoinColumn(name="id_jogador"),
            inverseJoinColumns={
                @JoinColumn(name="nome_cracha", referencedColumnName = "nome_cracha"),
                    @JoinColumn(name = "id_jogo", referencedColumnName = "id_jogo")
    })
    private Set<Cracha> crachas;

    public Jogador() { }

    public Integer getId() { return this.id; }

    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public String getEstado() { return this.estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Regiao getRegiao() { return this.regiao; }

    public void setRegiao(Regiao regiao) { this.regiao = regiao; }

    public Set<Jogador> getAmigos() { return this.amigos; }

    public void setAmigos(Set<Jogador> amigos) { this.amigos = amigos; }

    public void addAmigo(Jogador j){
        this.amigos.add(j);
    }

    public EstatisticasJogador getEstatisticas() { return this.estatisticas; }

    public void setEstatisticas(EstatisticasJogador estatisticas) { this.estatisticas = estatisticas; }

    public Set<Conversa> getConversas() { return this.conversas; }

    public void setConversas(Set<Conversa> conversas) { this.conversas = conversas; }


    public Set<Compra> getCompras() { return compras; }

    public void setCompras(Set<Compra> compras) { this.compras = compras; }

    public Compra addCompra(Compra compra) {
        getCompras().add(compra);
        compra.setJogador(this);
        return compra;
    }

    public Set<Cracha> getCrachas() { return this.crachas; }

    public void setCrachas(Set<Cracha> crachas) { this.crachas = crachas; }

}
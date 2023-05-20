package model.jogador;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import model.conversa.Conversa;
import model.cracha.Cracha;
import model.estatisticas_jogador.EstatisticasJogador;
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

    @OneToOne(mappedBy = "estatisticas_jogador")
    private EstatisticasJogador estatisticas;

    @ManyToMany(mappedBy="conversas",cascade=CascadeType.REMOVE)
    private Set<Conversa> conversas;


    //TODO arranjar a coluna 2 para aceitar 2 colunas
    @ManyToMany(cascade=CascadeType.REMOVE)
    @JoinTable(name="crachas_obtidos",
            joinColumns=@JoinColumn(name="id_jogador"),
            inverseJoinColumns=@JoinColumn(name="nome_cracha"))
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

    public EstatisticasJogador getEstatisticas() { return this.estatisticas; }

    public void setEstatisticas(EstatisticasJogador estatisticas) { this.estatisticas = estatisticas; }

    public Set<Conversa> getConversas() { return this.conversas; }

    public void setConversas(Set<Conversa> conversas) { this.conversas = conversas; }

    public Set<Cracha> getCrachas() { return this.crachas; }

    public void setCrachas(Set<Cracha> crachas) { this.crachas = crachas; }

}
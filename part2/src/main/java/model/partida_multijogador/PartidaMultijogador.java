package model.partida_multijogador;

import jakarta.persistence.*;
import model.jogo.Jogo;
import model.partida.Partida;
import model.partida.PartidaId;

@Entity(name = "partidas_multi_jogador")
@Table(name = "partidas_multi_jogador")
public class PartidaMultijogador {

    @EmbeddedId
    private PartidaId id;

    /*@JoinColumns({
            @JoinColumn(name = "id", referencedColumnName = "id"),
            @JoinColumn(name = "id_jogo", referencedColumnName = "id_jogo")
    })
    private Partida partida;
     */

    @ManyToOne
    @MapsId("idJogo")
    @JoinColumn(name = "id_jogo", referencedColumnName = "id")
    private Jogo jogo;

    private String estado;

    public PartidaMultijogador() { }

    public PartidaId getId() { return this.id; }

    public void setId(PartidaId id) { this.id = id; }

    public String getEstado(){ return this.estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Jogo getJogo() { return jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }
}

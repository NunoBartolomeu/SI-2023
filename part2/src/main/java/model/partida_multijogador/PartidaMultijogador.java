package model.partida_multijogador;

import jakarta.persistence.*;
import model.jogo.Jogo;
import model.partida.Partida;

@Entity(name = "partidas_multi_jogador")
public class PartidaMultijogador {

    @EmbeddedId
    private PartidaMultijogadorId id;

/*
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "id_partida", referencedColumnName = "id"),
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

    public PartidaMultijogadorId getId() { return this.id; }

    public void setId(PartidaMultijogadorId id) { this.id = id; }
/*
    public Partida getPartida() { return partida; }

    public void setPartida(Partida partida) { this.partida = partida; }

 */

    public String getEstado(){ return this.estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Jogo getJogo() { return jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }
}

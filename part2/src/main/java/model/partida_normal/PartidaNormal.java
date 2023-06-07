package model.partida_normal;

import jakarta.persistence.*;
import model.jogo.Jogo;
import model.partida.Partida;
import model.partida.PartidaId;
import model.regiao.Regiao;

@Entity(name = "partidas_normais")
public class PartidaNormal {

    @EmbeddedId
    private PartidaId id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "id_jogo", referencedColumnName = "id_jogo", insertable = false, updatable = false)
    })
    private Partida partida;

    @ManyToOne
    @MapsId("idJogo")
    @JoinColumn(name = "id_jogo", referencedColumnName = "id")
    private Jogo jogo;

    public PartidaNormal() { }

    private int dificuldade;

    public PartidaId getId() { return id; }

    public void setId(PartidaId id) { this.id = id; }

    public Jogo getJogo() { return jogo; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public int getDificuldade() { return this.dificuldade; }

    public void setDificuldade(int dificuldade) { this.dificuldade = dificuldade; }

    public Partida getPartida() { return partida; }

    public void setPartida(Partida partida) { this.partida = partida; }

}

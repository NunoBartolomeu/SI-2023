package model.partida_normal;

import jakarta.persistence.*;
import model.jogo.Jogo;
import model.partida.PartidaId;
import model.regiao.Regiao;
/*
@Entity(name = "partidas_normais")
public class PartidaNormal {
    @EmbeddedId
    @JoinColumn(name = "id_partida", referencedColumnName = "id")
    private PartidaId id;

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

}
*/
package model.partida_normal;

import jakarta.persistence.*;
import model.partida.Partida;

import java.io.Serializable;

@Embeddable
public class PartidaNormalId implements Serializable {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(insertable = false, updatable = false)
    private int id;

    @Column(name = "id_jogo")
    private String idJogo;

    @Column(name = "id_jogador", insertable = false, updatable = false)
    private int id_jogador;

    public PartidaNormalId() { }

    public long getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getIdJogo() { return this.idJogo; }

    public void setIdJogo(String idJogo) { this.idJogo = idJogo; }

    public int getId_jogador() { return id_jogador; }

    public void setId_jogador(int id_jogador) { this.id_jogador = id_jogador; }
}

package model.partida_multijogador;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PartidaMultijogadorId {

    @Column(name = "id_partida")
    private int id;

    @Column(name = "id_jogo", insertable = true, updatable = false)
    private String idJogo;

    public PartidaMultijogadorId() { }

    public long getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getIdJogo() { return this.idJogo; }

    public void setIdJogo(String idJogo) { this.idJogo = idJogo; }
}

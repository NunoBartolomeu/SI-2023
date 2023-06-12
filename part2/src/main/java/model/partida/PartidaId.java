package model.partida;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PartidaId implements Serializable {
    private int id;

    @Column(name = "id_jogo")
    private String idJogo;

    public PartidaId() { }

    public long getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getIdJogo() { return this.idJogo; }

    public void setIdJogo(String idJogo) { this.idJogo = idJogo; }
}

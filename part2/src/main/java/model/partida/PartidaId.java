package model.partida;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Embeddable
public class PartidaId {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_jogo", insertable = true, updatable = false)
    private String idJogo;

    public PartidaId() { }

    public long getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getIdJogo() { return this.idJogo; }

    public void setIdJogo(String idJogo) { this.idJogo = idJogo; }
}

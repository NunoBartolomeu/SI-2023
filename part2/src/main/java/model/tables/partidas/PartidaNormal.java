package model.partidas;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the partidas_normais database table.
 * 
 */
@Entity(name = "partidas_normais")
@Table(name = "partidas_normais")
@NamedQuery(name = "partidas_normais.findAll", query = "SELECT p FROM partidas_normais p")
public class PartidaNormal implements Serializable {
    @EmbeddedId
    private PartidaId id;

    private int dificuldade;

    
    public PartidaNormal() {
    }

    public PartidaId getId() {
        return this.id;
    }

    public void setId(PartidaId id) {
        this.id = id;
    }

    public int getDificuldade() {
        return this.dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

}
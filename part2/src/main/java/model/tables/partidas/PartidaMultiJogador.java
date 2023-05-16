package model.partidas;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the partidas_multi_jogadores database table.
 * 
 */
@Entity(name = "partidas_multi_jogadores")
@Table(name = "partidas_multi_jogadores")
@NamedQuery(name = "partidas_multi_jogadores.findAll", query = "SELECT p FROM partidas_multi_jogadores p")
public class PartidaMultiJogador implements Serializable {

    private static final List<String> estados = Arrays.asList("em curso", "terminada");

    @EmbeddedId
    private PartidaId id;

    private String estado;


    public PartidaMultiJogador() { }

    public PartidaId getId() { return this.id; }

    public void setId(PartidaId id) { this.id = id; }

    public String getEstado() { return this.estado; }

    public void setEstado(String estado) {
        if (estados.contains(estado))
            this.estado = estado;
        else 
            throw new Error("Estado: " + estado + "isn't valid!");
    }

}
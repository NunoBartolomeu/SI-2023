package model.user;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the amigos database table.
 * 
 */
@Entity(name = "amigos")
@Table(name = "amigos")
@NamedQuery(name = "amigos.findAll", query = "SELECT a FROM amigos a")
public class Amigo implements Serializable {
    @EmbeddedId
    private AmigoId id;
    
    public Amigo() {
    }

    public print() {
        System.out.println("Amigo: " + this.id.getId_jogador1() + " " + this.id.getId_jogador2());
    }
}

@Embeddable
public class AmigoId implements Serializable {
    private Integer id_jogador1;
    private Integer id_jogador2;

    public AmigoId() {
    }

    public AmigoId(Integer id_jogador1, Integer id_jogador2) {
        this.id_jogador1 = id_jogador1;
        this.id_jogador2 = id_jogador2;
    }

    public Integer getId_jogador1() {
        return this.id_jogador1;
    }

    public void setId_jogador1(Integer id_jogador1) {
        this.id_jogador1 = id_jogador1;
    }

    public Integer getId_jogador2() {
        return this.id_jogador2;
    }

    public void setId_jogador2(Integer id_jogador2) {
        this.id_jogador2 = id_jogador2;
    }

}

    
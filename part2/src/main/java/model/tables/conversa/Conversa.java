package model.conversa;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the conversas database table.
 * 
 */
@Entity
@Table(name = "conversas")
@NamedQuery(name = "Conversa.findAll", query = "SELECT c FROM Conversa c")
public class Conversa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_usuario1")
    private int idUsuario1;

    @Column(name = "id_usuario2")
    private int idUsuario2;

    public Conversa() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario1() {
        return this.idUsuario1;
    }

    public void setIdUsuario1(int idUsuario1) {
        this.idUsuario1 = idUsuario1;
    }

    public int getIdUsuario2() {
        return this.idUsuario2;
    }

    public void setIdUsuario2(int idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }

}
package model.user;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the jogadores database table.
 * 
 */
@Entity
@Table(name="jogadores")
@NamedQuery(name="Jogador.findAll", query="SELECT j FROM Jogador j")
public class Jogador implements Serializable {

}
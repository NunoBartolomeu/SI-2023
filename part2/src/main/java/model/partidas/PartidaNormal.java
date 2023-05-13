package model.partidas;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the partidas_normais database table.
 * 
 */
@Entity
@Table(name = "partidas_normais")
@NamedQuery(name = "PartidaNormal.findAll", query = "SELECT p FROM PartidaNormal p")
public class PartidaNormal extends Partida implements Serializable {

}
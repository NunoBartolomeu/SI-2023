package model.partidas;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the partidas_multi_jogadores database table.
 * 
 */
@Entity
@Table(name = "partidas_multi_jogadores")
@NamedQuery(name = "PartidaMultiJogador.findAll", query = "SELECT p FROM PartidaMultiJogador p")
public class PartidaMultiJogador extends Partida implements Serializable {

}

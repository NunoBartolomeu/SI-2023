package model.partidas;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the pontuacoes database table.
 * 
 */
@Entity
@Table(name = "pontuacoes")
@NamedQuery(name = "Pontuacao.findAll", query = "SELECT p FROM Pontuacao p")
public class Pontuacao implements Serializable {

}
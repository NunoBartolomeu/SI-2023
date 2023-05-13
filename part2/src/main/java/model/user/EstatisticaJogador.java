package model.user;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the estatisticas_jogador database table.
 * 
 */
@Entity
@Table(name = "estatisticas_jogador")
@NamedQuery(name = "EstatisticaJogador.findAll", query = "SELECT e FROM EstatisticaJogador e")
public class EstatisticaJogador implements Serializable {
    
}
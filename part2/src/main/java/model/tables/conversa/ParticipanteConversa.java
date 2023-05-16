package model.tables.conversa;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the participantes_conversa database table.
 * 
 */
@Entity
@Table(name = "participantes_conversa")
@NamedQuery(name = "ParticipanteConversa.findAll", query = "SELECT p FROM ParticipanteConversa p")
public class ParticipanteConversa implements Serializable {

}
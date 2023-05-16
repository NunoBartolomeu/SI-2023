package model.conversa;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the mensagens database table.
 * 
 */
@Entity
@Table(name = "mensagens")
@NamedQuery(name = "Mensagen.findAll", query = "SELECT m FROM Mensagen m")
public class Mensagen implements Serializable {
    }
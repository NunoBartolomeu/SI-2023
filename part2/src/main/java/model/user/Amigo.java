package model.user;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the amigos database table.
 * 
 */
@Entity
@Table(name = "amigos")
@NamedQuery(name = "Amigo.findAll", query = "SELECT a FROM Amigo a")
public class Amigo implements Serializable {

}
package model.tables.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the crachas_obtidos database table.
 * 
 */
@Entity
@Table(name = "crachas_obtidos")
@NamedQuery(name = "CrachaObtido.findAll", query = "SELECT c FROM CrachaObtido c")
public class CrachaObtido implements Serializable {

}
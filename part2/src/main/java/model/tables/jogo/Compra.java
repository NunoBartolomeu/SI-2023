package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the compras database table.
 * 
 */
@Entity
@Table(name = "compras")
@NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c")
public class Compra implements Serializable {
    }
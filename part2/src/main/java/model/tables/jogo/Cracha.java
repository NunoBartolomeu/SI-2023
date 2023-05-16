package model.jogo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the crachas database table.
 * 
 */
@Entity
@Table(name = "crachas")
@NamedQuery(name = "Cracha.findAll", query = "SELECT c FROM Cracha c")
public class Cracha implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cracha")
    private int idCracha;

    @Column(name = "nome_cracha")
    private String nomeCracha;

    @Column(name = "descricao_cracha")
    private String descricaoCracha;

    @Column(name = "imagem_cracha")
    private String imagemCracha;

    public Cracha() {
    }

    public int getIdCracha() {
        return this.idCracha;
    }

    public void setIdCracha(int idCracha) {
        this.idCracha = idCracha;
    }

    public String getNomeCracha() {
        return this.nomeCracha;
    }

    public void setNomeCracha(String nomeCracha) {
        this.nomeCracha = nomeCracha;
    }

    public String getDescricaoCracha() {
        return this.descricaoCracha;
    }

    public void setDescricaoCracha(String descricaoCracha) {
        this.descricaoCracha = descricaoCracha;
    }

    public String getImagemCracha() {
        return this.imagemCracha;
    }

    public void setImagemCracha(String imagemCracha) {
        this.imagemCracha = imagemCracha;
    }

}
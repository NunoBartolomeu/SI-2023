package model.cracha;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import model.jogo.Jogo;

@Embeddable
public class CrachaId {
    private String nome;
    @Column(name = "id_jogo")
    private String idJogo;

    public CrachaId() { }

    public String getNome() { return this.nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getJogo() { return this.idJogo; }

    public void setJogo(String idJogo) { this.idJogo = idJogo; }
}

package model.partida;

import jakarta.persistence.*;
import model.jogo.Jogo;
import model.partida_multijogador.PartidaMultijogador;
import model.partida_normal.PartidaNormal;
import model.regiao.Regiao;
import java.util.Date;

@Entity(name = "partidas")
@Table(name = "partidas")
public class Partida {
    @EmbeddedId
    private PartidaId id;

    @ManyToOne
    @MapsId("idJogo")
    @JoinColumn(name = "id_jogo")
    private Jogo jogo;

    @OneToOne(mappedBy = "partida")
    private PartidaMultijogador partidaMultijogador;

    @OneToOne(mappedBy = "partida")
    private PartidaNormal partidaNormal;

    private boolean isNormal;

    private Date data_inicio;

    private Date data_fim;

    @JoinColumn(name = "regiao")
    private Regiao regiao;

    public Partida() { }

    public PartidaMultijogador getPartidaMultijogador() { return partidaMultijogador; }

    public void setPartidaMultijogador(PartidaMultijogador partidaMultijogador) { this.partidaMultijogador = partidaMultijogador; }

    public PartidaNormal getPartidaNormal() { return partidaNormal; }

    public void setPartidaNormal(PartidaNormal partidaNormal) { this.partidaNormal = partidaNormal; }

    public boolean getIsNormal() { return isNormal; }

    public void setNormal(boolean normal) { isNormal = normal; }

    public PartidaId getId() { return this.id; }

    public void setId(PartidaId id) { this.id = id; }

    public Date getData_inicio() { return this.data_inicio; }

    public void setData_inicio(Date data_inicio) { this.data_inicio = data_inicio; }

    public Date getData_fim() { return this.data_fim; }

    public void setData_fim(Date data_fim) { this.data_fim = data_fim; }

    public Regiao getRegiao() { return this.regiao; }

    public void setRegiao(Regiao regiao) { this.regiao = regiao; }

    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public Jogo getJogo() { return this.jogo; }

/*
    public Set<PartidaNormal> getPartidasNormais() { return partidasNormais; }

    public void setPartidasNormais(Set<PartidaNormal> partidasNormais) { this.partidasNormais = partidasNormais; }

    public Set<PartidaMultijogador> getPartidasMultijogador() { return partidasMultijogador; }

    public void setPartidasMultijogador(Set<PartidaMultijogador> partidasMultijogador) { this.partidasMultijogador = partidasMultijogador; }


 */
}

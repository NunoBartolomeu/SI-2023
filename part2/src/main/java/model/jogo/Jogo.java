package model.jogo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import model.estatisticas_jogo.EstatisticasJogo;

@Entity(name = "jogos")
@Table(name = "jogos")
public class Jogo {
    @Id
    private String id;

    private String nmome;

    private String url;

    @OneToOne(mappedBy = "estatisticas_jogador")
    private EstatisticasJogo estatisticas;

    public Jogo() { }

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public String getNmome() { return this.nmome; }

    public void setNmome(String nmome) { this.nmome = nmome; }

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    public EstatisticasJogo getEstatisticas() { return this.estatisticas; }

    public void setEstatisticas(EstatisticasJogo estatisticas) { this.estatisticas = estatisticas; }

}

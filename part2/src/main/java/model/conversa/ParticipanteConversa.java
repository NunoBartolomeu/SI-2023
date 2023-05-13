package model.conversa;

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
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participante_conversa")
    private Integer idParticipanteConversa;

    // bi-directional many-to-one association to Conversa
    @ManyToOne
    @JoinColumn(name = "id_conversa")
    private Conversa conversa;

    // bi-directional many-to-one association to Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public ParticipanteConversa() {
    }

    public Integer getIdParticipanteConversa() {
        return this.idParticipanteConversa;
    }

    public void setIdParticipanteConversa(Integer idParticipanteConversa) {
        this.idParticipanteConversa = idParticipanteConversa;
    }

    public Conversa getConversa() {
        return this.conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
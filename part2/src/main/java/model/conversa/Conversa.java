package model.conversa;

import jakarta.persistence.*;
import model.jogador.Jogador;
import model.mensagen.Mensagem;

import java.util.Set;

@Entity(name = "conversas")
@Table(name = "conversas")
public class Conversa {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String nome;

    @ManyToMany(cascade=CascadeType.REMOVE)
    @JoinTable(name="participantes_conversa",
            joinColumns=@JoinColumn(name="id_conversa"),
            inverseJoinColumns=@JoinColumn(name="id_jogador"))
    private Set<Jogador> participantes;

    @OneToMany(mappedBy = "conversa", cascade = CascadeType.REMOVE)
    private Set<Mensagem> mensagens;

    public Conversa() { }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getNome() { return this.nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Set<Jogador> getParticipantes() { return this.participantes; }

    public void setParticipantes(Set<Jogador> participantes) { this.participantes = participantes; }

    public Set<Mensagem> getMensagens() { return mensagens; }

    public void setMensagens(Set<Mensagem> mensagens) { this.mensagens = mensagens;}

    public Mensagem addMensagem(Mensagem mensagem) {
        getMensagens().add(mensagem);
        mensagem.setConversa(this);
        return mensagem;
    }


}
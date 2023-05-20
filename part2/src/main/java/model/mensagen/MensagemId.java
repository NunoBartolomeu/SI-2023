package model.mensagen;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Embeddable
public class MensagemId {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numero;

    private int id_conversa;

    public MensagemId() { }

    public long getNumero() { return this.numero; }

    public void setNumero(long numero) { this.numero = numero; }

    public int getId_conversa() { return this.id_conversa; }

    public void setId_conversa(int id_conversa) { this.id_conversa = id_conversa; }

}
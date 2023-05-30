package model.mensagen;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Embeddable
public class MensagemId {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    private int idConversa;

    public MensagemId() { }

    public long getNumero() { return this.numero; }

    public void setNumero(int numero) { this.numero = numero; }

    public int getIdConversa() { return this.idConversa; }

    public void setIdConversa(int idConversa) { this.idConversa = idConversa; }

}

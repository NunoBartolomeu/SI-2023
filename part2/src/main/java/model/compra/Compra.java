package model.compra;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity(name = "compras")
@Table(name = "compras")
public class Compra {
    @EmbeddedId
    private CompraId id;

    private Date dataDeCompra;

    private Float preco;

    public Compra() { }

    public CompraId getId() { return this.id; }

    public void setId(CompraId id) { this.id = id; }

    public Date getDataDeCompra() { return this.dataDeCompra; }

    public void setDataDeCompra(Date dataDeCompra) { this.dataDeCompra = dataDeCompra; }

    public Float getPreco() { return this.preco; }

    public void setPreco(Float preco) { this.preco = preco; }
}

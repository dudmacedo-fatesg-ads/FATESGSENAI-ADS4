package br.com.eduardo.floricultura.model;

import br.com.eduardo.floricultura.dal.ProdutoDAO;
import br.com.eduardo.floricultura.util.DatabaseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class ItemPedido {

    private Pedido pedido;
    private Produto produto;
    private double quantidade;
    private double valor;

    public long getProdutocodigo() {
        return produto.getCodigo();
    }

    public String getProdutonome() {
        if (produto.getNome() == null) {
            try {
                produto = new ProdutoDAO().retrieve(produto.getCodigo());
            } catch (DatabaseException ex) {
                Logger.getLogger(ItemPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return produto.getNome();
    }

    public String getProdutounidade() {
        if (produto.getUnidade()== null) {
            try {
                produto = new ProdutoDAO().retrieve(produto.getCodigo());
            } catch (DatabaseException ex) {
                Logger.getLogger(ItemPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return produto.getUnidade();
    }
    
    // Getters e Setters padrão
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

package br.com.eduardo.ads4.mmqspp.decorator.model;

/**
 * @author eduardo
 */
public abstract class Computador {
    protected String produto;
    protected double valor;
    
    public Computador(String produto, double valor) {
        this.produto = produto;
        this.valor = valor;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
    
    public String getDescricao() {
        return produto;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public double getValor() {
        return valor;
    }
}

package br.com.eduardo.ads4.mmqspp.observer;

/**
 *
 * @author eduardo
 */
public class Lance {
    private final Licitante licitante;
    private final double valor;
    
    public Lance(Licitante licitante, double valor) {
        this.licitante = licitante;
        this.valor = valor;
    }

    public Licitante getLicitante() {
        return licitante;
    }

    public double getValor() {
        return valor;
    }
}

package br.com.eduardo.ads4.mmqspp.decorator.model;

/**
 *
 * @author eduardo
 */
public class SSD extends Especificacao {

    public SSD(String descricao, double valor, Computador computador) {
        super(descricao, valor, computador);
    }

    @Override
    public String getDescricao() {
        return computador.getDescricao() + ", SSD: " + descricao;
    }
    
}

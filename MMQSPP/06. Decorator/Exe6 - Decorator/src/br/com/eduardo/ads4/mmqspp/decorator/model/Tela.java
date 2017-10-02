package br.com.eduardo.ads4.mmqspp.decorator.model;

/**
 *
 * @author eduardo
 */
public class Tela extends Especificacao {

    public Tela(String descricao, double valor, Computador computador) {
        super(descricao, valor, computador);
    }

    @Override
    public String getDescricao() {
        return computador.getDescricao() + ", Tela: " + descricao;
    }
    
}

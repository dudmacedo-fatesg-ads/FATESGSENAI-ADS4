package br.com.eduardo.ads4.mmqspp.decorator.model;

/**
 *
 * @author eduardo
 */
public class Processador extends Especificacao {
    public Processador(String descricao, double valor, Computador computador) {
        super(descricao, valor, computador);
    }

    @Override
    public String getDescricao() {
        return computador.getDescricao() + ", Processador: " + descricao;
    }
    
}

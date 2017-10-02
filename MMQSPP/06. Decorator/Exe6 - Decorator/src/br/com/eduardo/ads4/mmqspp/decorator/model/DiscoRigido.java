package br.com.eduardo.ads4.mmqspp.decorator.model;

/**
 *
 * @author eduardo
 */
public class DiscoRigido extends Especificacao {

    public DiscoRigido(String descricao, double valor, Computador computador) {
        super(descricao, valor, computador);
    }

    @Override
    public String getDescricao() {
        return computador.getDescricao() + ", Disco Rígido: " + descricao;
    }
    
}

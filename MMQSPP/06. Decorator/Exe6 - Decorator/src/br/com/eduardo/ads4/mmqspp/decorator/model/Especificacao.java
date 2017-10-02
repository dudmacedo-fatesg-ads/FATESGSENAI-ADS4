package br.com.eduardo.ads4.mmqspp.decorator.model;

/**
 *
 * @author eduardo
 */
public abstract class Especificacao extends Computador {

    protected String descricao;
    protected Computador computador;

    public Especificacao(String descricao, double valor, Computador computador) {
        super(null, valor);
        this.descricao = descricao;
        this.computador = computador;
    }

    @Override
    public abstract String getDescricao();

    @Override
    public double getValor() {
        return computador.getValor() + valor;
    }
}

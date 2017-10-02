package br.com.eduardo.ads4.mmqspp.decorator.model;

/**
 *
 * @author eduardo
 */
public class PlacaVideo extends Especificacao{

    public PlacaVideo(String descricao, double valor, Computador computador) {
        super(descricao, valor, computador);
    }

    @Override
    public String getDescricao() {
        return computador.getDescricao() + ", Placa de Vídeo: " + descricao;
    }
    
}

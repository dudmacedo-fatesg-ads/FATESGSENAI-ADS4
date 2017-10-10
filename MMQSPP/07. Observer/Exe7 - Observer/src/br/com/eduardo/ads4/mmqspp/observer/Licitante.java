package br.com.eduardo.ads4.mmqspp.observer;

/**
 *
 * @author eduardo
 */
public class Licitante extends Expectador {
    
    private final Pregoeiro pregoeiro;

    public Licitante(String nome, Pregoeiro pregoeiro) {
        super(nome);
        this.pregoeiro = pregoeiro;
    }
    
    public void registraLance(double lance) {
        System.out.println(String.format("%s tenta lance de R$ %.2f", this.nome, lance));
        pregoeiro.recebeLance(new Lance(this, lance));
    }

}

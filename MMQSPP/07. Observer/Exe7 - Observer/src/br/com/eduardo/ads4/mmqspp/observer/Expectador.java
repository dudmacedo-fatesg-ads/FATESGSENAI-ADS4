package br.com.eduardo.ads4.mmqspp.observer;

/**
 *
 * @author eduardo
 */
public class Expectador implements Observer {

    protected final String nome;
    private Lance ultimo_lance;

    public Expectador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }

    @Override
    public void notificar(Lance ultimo_lance) {
        this.ultimo_lance = ultimo_lance;
        System.out.println(
                String.format(
                        "Observer %s notificado - Último lance: R$ %.2f - Licitante: %s",
                        nome,
                        this.ultimo_lance.getValor(),
                        this.ultimo_lance.getLicitante().getNome()));
    }
}

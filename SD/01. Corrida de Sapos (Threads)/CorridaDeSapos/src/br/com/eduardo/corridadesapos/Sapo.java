package br.com.eduardo.corridadesapos;

/**
 *
 * @author eduardo
 */
public class Sapo implements Runnable {
    private int id;
    private int progresso;

    public Sapo(int id) {
        this.id = id;
        progresso = 0;
    }
    
    public int getId() {
        return id;
    }
    public int getProgresso() {
        return progresso;
    }
    
    @Override
    public void run() {
        while (progresso < Podio.percurso) {
            progresso++;
        }
        Podio.finalizou(this);
    }
    
    
}

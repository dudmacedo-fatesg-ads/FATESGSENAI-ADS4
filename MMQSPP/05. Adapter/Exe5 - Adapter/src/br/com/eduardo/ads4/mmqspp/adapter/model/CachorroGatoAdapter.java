
package br.com.eduardo.ads4.mmqspp.adapter.model;

/**
 *
 * @author eduardo
 */
public class CachorroGatoAdapter extends Cachorro {
    private Gato gato;
    
    public CachorroGatoAdapter(Gato gato) {
        this.gato = gato;
    }
    
    @Override
    public void latir() {
        gato.miar();
    }
}

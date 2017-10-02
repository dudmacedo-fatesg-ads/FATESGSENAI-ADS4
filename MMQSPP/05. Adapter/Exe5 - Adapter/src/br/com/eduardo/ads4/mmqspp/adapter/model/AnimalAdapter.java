package br.com.eduardo.ads4.mmqspp.adapter.model;

/**
 *
 * @author eduardo
 */
public class AnimalAdapter {
    private Animal animal;
    
    public AnimalAdapter(Animal animal) {
        this.animal = animal;
    }

    public void fazBarulho() {
        if (animal.getClass().getName().equals("br.com.eduardo.ads4.mmqspp.adapter.model.Cachorro")) {
            ((Cachorro)animal).latir();
        }
        else if (animal.getClass().getName().equals("br.com.eduardo.ads4.mmqspp.adapter.model.Gato")) {
            ((Gato)animal).miar();
        }
        else if (animal.getClass().getName().equals("br.com.eduardo.ads4.mmqspp.adapter.model.Vaca")) {
            ((Vaca)animal).mugir();
        }
        else {
            System.out.println("Animal não identificado no Adapter.");
        }
    }
    
}

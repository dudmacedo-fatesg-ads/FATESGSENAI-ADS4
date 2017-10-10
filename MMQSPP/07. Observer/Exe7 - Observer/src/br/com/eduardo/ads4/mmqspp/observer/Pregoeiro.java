package br.com.eduardo.ads4.mmqspp.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class Pregoeiro implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private Lance ultimo_lance;

    public Pregoeiro(double lance_inicial) {
        ultimo_lance = new Lance(null, lance_inicial);
    }

    public void recebeLance(Lance lance) {
//        synchronized (this) {
        if (ultimo_lance.getValor() < lance.getValor()) {
            System.out.println("Lance Aceito");
            ultimo_lance = lance;
            notificaObservers();
        } else {
            System.out.println("Lance Recusado.");
        }
//        }
    }

    @Override
    public void adicionaObserver(Observer observer) {
        observers.add(observer);
        System.out.println(observer.toString() + " Entrou.");
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println(observer.toString() + " Saiu.");
    }

    @Override
    public void notificaObservers() {
        observers.forEach((o) -> {
            o.notificar(ultimo_lance);
        });
    }
}

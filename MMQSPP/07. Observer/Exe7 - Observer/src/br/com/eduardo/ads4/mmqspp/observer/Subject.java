package br.com.eduardo.ads4.mmqspp.observer;

/**
 *
 * @author eduardo
 */
public interface Subject {

    public void adicionaObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notificaObservers();
}

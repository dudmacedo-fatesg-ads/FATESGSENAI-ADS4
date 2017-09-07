package br.com.eduardo.ads4.sd.mergesort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class MultiThreadingMergeSort extends MergeSort implements Runnable {

    private Integer inicio = null;
    private Integer fim = null;

    public MultiThreadingMergeSort(int[] vetor) {
        super(vetor);
        inicio = 0;
        fim = vetor.length - 1;
    }

    private MultiThreadingMergeSort(int[] vetor, int inicio, int fim) {
        super(vetor);
        this.inicio = inicio;
        this.fim = fim;

    }

    @Override
    public void run() {
        ordena();
    }

    @Override
    public void ordenar() {
//        Thread t = new Thread(new MultiThreadingMergeSort(vetor, inicio, fim));
//        t.start();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Thread(new MultiThreadingMergeSort(vetor, inicio, fim)));

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void ordena() {
        int meio = (inicio + fim) / 2;
        if (inicio < fim) {
//            Thread left = new Thread(new MultiThreadingMergeSort(vetor, inicio, meio));
//            left.start();
//            Thread right = new Thread(new MultiThreadingMergeSort(vetor, meio + 1, fim));
//            right.start();

            ExecutorService executor = Executors.newFixedThreadPool(2);

            executor.execute(new Thread(new MultiThreadingMergeSort(vetor, inicio, meio)));
            executor.execute(new Thread(new MultiThreadingMergeSort(vetor, meio + 1, fim)));

            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        merge(inicio, meio, fim);
    }

}

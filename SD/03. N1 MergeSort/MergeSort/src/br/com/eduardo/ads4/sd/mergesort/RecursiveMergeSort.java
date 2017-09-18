package br.com.eduardo.ads4.sd.mergesort;

/**
 *
 * @author eduardo
 */
public class RecursiveMergeSort extends MergeSort {

    public RecursiveMergeSort(int[] vetor) {
        super(vetor);
    }

    @Override
    public int[] ordenar() {
        ordenar(0, vetor.length - 1);
        return (vetor);
    }

    private void ordenar(int inicio, int fim) {
        int meio = (inicio + fim) / 2;
        if (inicio < fim) {
            ordenar(inicio, meio);
            ordenar(meio + 1, fim);
        }

        merge(inicio, meio, fim);
    }
}

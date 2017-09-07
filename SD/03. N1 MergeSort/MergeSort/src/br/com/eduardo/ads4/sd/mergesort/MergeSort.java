package br.com.eduardo.ads4.sd.mergesort;

/**
 *
 * @author eduardo
 */
public abstract class MergeSort {

    protected int[] vetor;

    public MergeSort(int[] vetor) {
        this.vetor = vetor;
    }

    public int[] getVetor() {
        return vetor;
    }

    public abstract void ordenar();

    protected void merge(int inicio, int meio, int fim) {
        int[] aux = new int[vetor.length];

        for (int i = inicio; i <= fim; i++) {
            aux[i] = vetor[i];
        }

        int i = inicio;
        int j = meio + 1;
        int k = inicio;

        while (i <= meio && j <= fim) {
            if (aux[i] < aux[j]) {
                vetor[k] = aux[i++];
            } else {
                vetor[k] = aux[j++];
            }
            k++;
        }

        //Append de itens que não foram usados na Junção
        while (i <= meio) {
            vetor[k++] = aux[i++];
        }

        //Append de itens que não foram usados na Junção
        while (j <= fim) {
            vetor[k++] = aux[j++];
        }
    }

}

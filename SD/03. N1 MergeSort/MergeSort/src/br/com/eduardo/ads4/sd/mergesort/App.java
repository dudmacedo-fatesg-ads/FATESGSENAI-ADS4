package br.com.eduardo.ads4.sd.mergesort;

import java.util.Random;

/**
 *
 * @author eduardo
 */
public class App {

    public static int nthreads;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nelementos = 10000;
        int limitevalor = 1000000;
        
        System.out.println("*** Merge Sort ***");
        System.out.println("Tamanho do vetor: " + nelementos);
        System.out.println("Limite de valor em cada posição: " + limitevalor);
        System.out.println();

        // Criando e populando o vetor a ser ordenado
        System.out.print("Criando vetor a ser ordenado... ");
        Random rnd = new Random(System.currentTimeMillis());
        int[] vetor = new int[nelementos];
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = rnd.nextInt(limitevalor);
        }
        System.out.println("OK\n");

        // Ordenação via método recursivo
        System.out.println("// Ordenação com método recursivo:");
        long Ini = System.currentTimeMillis();
        MergeSort recMergeSort = new RecursiveMergeSort(vetor.clone());
        recMergeSort.ordenar();
        long Diff = System.currentTimeMillis() - Ini;
        System.out.println("Tempo de Execução: "
                + String.format(
                        "%d:%02d:%02d.%03d", 
                        (Diff / 3600000) % 60,
                        (Diff / 60000) % 60,
                        (Diff / 1000) % 60,
                        Diff % 1000));

        // Ordenação com MultiThreading
        System.out.println("// Ordenação com multithreading:");
        nthreads = 0;
        Ini = System.currentTimeMillis();
        MergeSort trdMergeSort = new MultiThreadingMergeSort(vetor.clone());
//        int[] trdvetor = trdMergeSort.ordenar();
        trdMergeSort.ordenar();
        Diff = System.currentTimeMillis() - Ini;
        System.out.println(nthreads + " Threads abertas.");
        System.out.println("Tempo de Execução: "
                + String.format(
                        "%d:%02d:%02d.%03d", 
                        (Diff / 3600000) % 60,
                        (Diff / 60000) % 60,
                        (Diff / 1000) % 60,
                        Diff % 1000));
//        printVetor(trdvetor);
    }
    
//    private static void printVetor(int[] vetor) {
//        String print = "[";
//        for (int i = 0; i < vetor.length; i++) {
//            print += String.format("%03d", vetor[i]);
//            if (i != vetor.length - 1) {
//                print += "][";
//            }
//        }
//        print += "]";
//        
//        System.out.println(print);
//    }
    
}

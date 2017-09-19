package br.com.eduardo.ads4.mmqspp.iterator.persistence;

import br.com.eduardo.ads4.mmqspp.iterator.App;
import br.com.eduardo.ads4.mmqspp.iterator.model.Professor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author eduardo
 */
public abstract class PProfessor {

    protected final File arquivo;
    private int tp_estrutura;

    public PProfessor(File arquivo, int tp_estrutura) {
        this.arquivo = arquivo;
        this.tp_estrutura = tp_estrutura;
    }

    // Retorna:
    // 0 = objetos iguais
    // < 0 = a1 menor que a2
    // > 0 = a1 maior que a2
    public abstract int comparar(Professor p1, Professor p2);

    public Iterator<Professor> listarProfessores() throws FileNotFoundException, IOException {
        IPProfessorEstrutura estrutura = null;

        switch (tp_estrutura) {
            case 3:
                estrutura = new PProfessorEstrLista(this);
                break;
        }

        return (estrutura != null) ? estrutura.getIterator() : null;
    }

    private void ordenar(ArrayList<Professor> professores) {
        new MultiThreadingMergeSort(professores).ordenar();
    }

    private class MultiThreadingMergeSort implements Runnable {

        private ArrayList<Professor> professores;
        private int inicio;
        private int fim;

        public MultiThreadingMergeSort(ArrayList<Professor> professores) {
            this.professores = professores;
            this.inicio = 0;
            this.fim = professores.size() - 1;
        }

        public MultiThreadingMergeSort(ArrayList<Professor> professores, int inicio, int fim) {
            this.professores = professores;
            this.inicio = inicio;
            this.fim = fim;
        }

        @Override
        public void run() {
            ordena();
        }

        public void ordenar() {

            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.execute(new Thread(new MultiThreadingMergeSort(professores, inicio, fim)));

            executor.shutdown();

            try {
                executor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        private void ordena() {
            int meio = (inicio + fim) / 2;
            if (inicio < fim && inicio + 1 < fim) {
                ExecutorService executor = Executors.newFixedThreadPool(2);

                executor.execute(new Thread(new MultiThreadingMergeSort(professores, inicio, meio)));
                executor.execute(new Thread(new MultiThreadingMergeSort(professores, meio + 1, fim)));
                App.nthreads += 2;

                executor.shutdown();
                try {
                    executor.awaitTermination(1, TimeUnit.DAYS);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            merge(inicio, meio, fim);
        }

        protected void merge(int inicio, int meio, int fim) {
            Professor[] aux = new Professor[professores.size()];

            for (int i = inicio; i <= fim; i++) {
                aux[i] = professores.get(i);
            }

            int i = inicio;
            int j = meio + 1;
            int k = inicio;

            while (i <= meio && j <= fim) {
                if (comparar(aux[i], aux[j]) < 0) {
                    professores.set(k, aux[i++]);
                } else {
                    professores.set(k, aux[j++]);
                }
                k++;
            }

            //Append de itens que não foram usados na Junção
            while (i <= meio) {
                professores.set(k++, aux[i++]);
            }

            //Append de itens que não foram usados na Junção
            while (j <= fim) {
                professores.set(k++, aux[j++]);
            }
        }
    }
}

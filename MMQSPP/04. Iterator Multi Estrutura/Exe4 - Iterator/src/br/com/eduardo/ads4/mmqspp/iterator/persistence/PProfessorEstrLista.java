package br.com.eduardo.ads4.mmqspp.iterator.persistence;

import br.com.eduardo.ads4.mmqspp.iterator.model.Professor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author eduardo
 */
public class PProfessorEstrLista implements IPProfessorEstrutura {

    private PProfessor pprofessor;
    private List<Professor> professores;
    private int inicio;
    private int fim;

    public PProfessorEstrLista(PProfessor pprofessor) {
        this.pprofessor = pprofessor;
        try (FileReader fr = new FileReader(pprofessor.arquivo)) {
            professores = new LinkedList<>();

            BufferedReader br = new BufferedReader(fr);
            if (br.ready()) {
                br.readLine();
            }
            while (br.ready()) {
                String[] linha = br.readLine().split(";");

                professores.add(
                        new Professor(
                                Integer.parseInt(linha[0]),
                                linha[1],
                                linha[2],
                                linha[3],
                                linha[4],
                                Integer.parseInt(linha[5]),
                                Integer.parseInt(linha[6])
                        ));
            }
            br.close();
            fr.close();

            ordenar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private PProfessorEstrLista(PProfessor pprofessor, List<Professor> professores, int inicio, int fim) {
        this.pprofessor = pprofessor;
        this.professores = professores;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    public void run() {
        ordena();
    }

    private void ordena() {
        int meio = (inicio + fim) / 2;
        if (inicio + 1 < fim) {
            ExecutorService executor = Executors.newFixedThreadPool(2);

            executor.execute(new Thread(new PProfessorEstrLista(pprofessor, professores, inicio, meio)));
            executor.execute(new Thread(new PProfessorEstrLista(pprofessor, professores, meio + 1, fim)));

            executor.shutdown();

            try {
                executor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        merge(inicio, meio, fim);
    }

    private void merge(int inicio, int meio, int fim) {
        Professor[] aux = new Professor[professores.size()];

        for (int i = inicio; i <= fim; i++) {
            aux[i] = professores.get(i);
        }

        int i = inicio;
        int j = meio + 1;
        int k = inicio;

        while (i <= meio && j <= fim) {
            if (pprofessor.comparar(aux[i], aux[j]) < 0) {
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

    private void ordenar() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Thread(new PProfessorEstrLista(pprofessor, professores, 0, professores.size() - 1)));

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Iterator<Professor> getIterator() {
        return professores.iterator();
    }
}

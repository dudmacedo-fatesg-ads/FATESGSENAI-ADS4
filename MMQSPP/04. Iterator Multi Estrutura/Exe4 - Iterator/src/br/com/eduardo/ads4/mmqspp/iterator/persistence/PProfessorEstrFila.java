package br.com.eduardo.ads4.mmqspp.iterator.persistence;

import br.com.eduardo.ads4.mmqspp.iterator.model.Professor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author eduardo
 */
public class PProfessorEstrFila implements IPProfessorEstrutura {

    private PProfessor pprofessor;
    private final ConcurrentLinkedQueue<Professor> professores;
    private int inicio;
    private int fim;

    public PProfessorEstrFila(PProfessor pprofessor) {
        this.pprofessor = pprofessor;
        professores = new ConcurrentLinkedQueue<>();
        try (FileReader fr = new FileReader(pprofessor.arquivo)) {

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
            ex.printStackTrace(System.out);
        }
    }

    private PProfessorEstrFila(PProfessor pprofessor, ConcurrentLinkedQueue<Professor> professores, int inicio, int fim) {
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

            executor.execute(new Thread(new PProfessorEstrFila(pprofessor, professores, inicio, meio)));
            executor.execute(new Thread(new PProfessorEstrFila(pprofessor, professores, meio + 1, fim)));

            executor.shutdown();

            try {
                executor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }

        merge(inicio, meio, fim);
    }

    private void merge(int inicio, int meio, int fim) {
        synchronized (professores) {
            Professor[] aux = new Professor[professores.size()];

            Iterator<Professor> it = professores.iterator();
            int cont = 0;
            while (cont < inicio) {
                it.next();
                cont++;
            }
            for (int i = inicio; i <= fim; i++) {
                aux[i] = it.next();
            }

            int i = inicio;
            int j = meio + 1;
            int k = inicio;

            while (i <= meio && j <= fim) {
                if (pprofessor.comparar(aux[i], aux[j]) < 0) {
                    setProfessoresItem(k, aux[i++]);
                } else {
                    setProfessoresItem(k, aux[j++]);
                }
                k++;
            }

            //Append de itens que não foram usados na Junção
            while (i <= meio) {
                setProfessoresItem(k++, aux[i++]);
            }

            //Append de itens que não foram usados na Junção
            while (j <= fim) {
                setProfessoresItem(k++, aux[j++]);
            }
        }
    }

    private void setProfessoresItem(int idx, Professor professor) {
        synchronized (professores) {
            ConcurrentLinkedQueue<Professor> newqueue = new ConcurrentLinkedQueue<>();
            professores.remove(professor);
            Iterator<Professor> it = professores.iterator();
            int cont = 0;
            while (cont < idx) {
                newqueue.add(it.next());
                cont++;
            }
            newqueue.add(professor);
            while (it.hasNext()) {
                newqueue.add(it.next());
            }
            professores.removeAll(professores);
            professores.addAll(newqueue);
        }
    }

    private void ordenar() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Thread(new PProfessorEstrFila(pprofessor, professores, 0, professores.size() - 1)));

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

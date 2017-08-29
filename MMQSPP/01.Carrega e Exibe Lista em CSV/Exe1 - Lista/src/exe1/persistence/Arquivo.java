package exe1.persistence;

import exe1.model.Departamento;
import exe1.model.Professor;
import exe1.model.Regime;
import exe1.model.Titulacao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author dudma
 */
public class Arquivo {

//    private Comparator comp;
//    public Arquivo(Comparator comp) {
//        this.comp = comp;
//    }
    public static ArrayList<Professor> getProfessores(File arq) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(arq);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<Professor> lista = new ArrayList<>();

        while (br.ready()) {
            String[] linha = br.readLine().split(";");

            lista.add(
                    new Professor(
                            Integer.parseInt(linha[0].trim()),
                            linha[1],
                            Departamento.getDepartamento(linha[2]),
                            Titulacao.getTitulacao(linha[3]),
                            Regime.getRegime(linha[4]),
                            Integer.parseInt(linha[5].trim()),
                            Integer.parseInt(linha[6].trim())
                    ));
        }

        lista.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Professor) o1).getNome()
                        .compareToIgnoreCase(((Professor) o2).getNome());
            }
        });

        br.close();
        fr.close();

        return lista;
    }

    public Iterator<Professor> getProfessoresList(File arq) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(arq);
        BufferedReader br = new BufferedReader(fr);

        List<Professor> lista = new LinkedList<>();

        while (br.ready()) {
            String[] linha = br.readLine().split(";");

            lista.add(
                    new Professor(
                            Integer.parseInt(linha[0]),
                            linha[1],
                            Departamento.getDepartamento(linha[2]),
                            Titulacao.getTitulacao(linha[3]),
                            Regime.getRegime(linha[4]),
                            Integer.parseInt(linha[5]),
                            Integer.parseInt(linha[6])
                    ));
        }

        lista.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Professor) o1).getNome()
                        .compareToIgnoreCase(((Professor) o2).getNome());
            }
        });

        br.close();
        fr.close();

        return lista.iterator();
    }

    public Iterator<Professor> getProfessoresPilha(File arq) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(arq);
        BufferedReader br = new BufferedReader(fr);

        Stack<Professor> retorno = new Stack<>();

        while (br.ready()) {
            String[] linha = br.readLine().split(";");

            retorno.push(
                    new Professor(
                            Integer.parseInt(linha[0]),
                            linha[1],
                            Departamento.getDepartamento(linha[2]),
                            Titulacao.getTitulacao(linha[3]),
                            Regime.getRegime(linha[4]),
                            Integer.parseInt(linha[5]),
                            Integer.parseInt(linha[6])
                    ));
        }

        retorno.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Professor) o1).getNome()
                        .compareToIgnoreCase(((Professor) o2).getNome());
            }
        });

        br.close();
        fr.close();

        return retorno.iterator();
    }

//    public Iterator<Professor> getProfessoresArrayList(File arq) throws FileNotFoundException, IOException {
//        FileReader fr = new FileReader(arq);
//        BufferedReader br = new BufferedReader(fr);
//
//        ArrayList<Professor> lista = new ArrayList<>();
//
//        while (br.ready()) {
//            String[] linha = br.readLine().split(";");
//
//            lista.add(
//                    new Professor(
//                            Integer.parseInt(linha[0]),
//                            linha[1],
//                            Departamento.getDepartamento(linha[2]),
//                            Titulacao.getTitulacao(linha[3]),
//                            Regime.getRegime(linha[4]),
//                            Integer.parseInt(linha[5]),
//                            Integer.parseInt(linha[6])
//                    ));
//        }
//
//        lista.sort(comp);
//
//        br.close();
//        fr.close();
//
//        return lista.iterator();
//    }
//    public Iterator<Aluno> getAlunosSet(File arq) throws FileNotFoundException, IOException {
//        FileReader fr = new FileReader(arq);
//        BufferedReader br = new BufferedReader(fr);
//
//        Set<Aluno> retorno = new TreeSet<>(comp);
//
//        while (br.ready()) {
//            String[] linha = br.readLine().split(";");
//
//            retorno.add(
//                    new Aluno(
//                            new Matricula(linha[0]),
//                            linha[1],
//                            Disciplina.getDisciplinas(linha[2])));
//        }
//
//        br.close();
//        fr.close();
//
//        return retorno.iterator();
//    }
//    public Iterator<Aluno> getAlunosArvore(File arq) throws FileNotFoundException, IOException {
//        FileReader fr = new FileReader(arq);
//        BufferedReader br = new BufferedReader(fr);
//
//        TreeSet<Aluno> retorno = new TreeSet<>(comp);
//
//        while (br.ready()) {
//            String[] linha = br.readLine().split(";");
//
//            retorno.add(
//                    new Aluno(
//                            new Matricula(linha[0]),
//                            linha[1],
//                            Disciplina.getDisciplinas(linha[2])));
//        }
//
//        br.close();
//        fr.close();
//
//        return retorno.iterator();
//    }
//
//    public Iterator<Aluno> getAlunosFila(File arq) throws FileNotFoundException, IOException {
//        FileReader fr = new FileReader(arq);
//        BufferedReader br = new BufferedReader(fr);
//
//        Queue<Aluno> fila = new LinkedBlockingQueue<>();
//
//        while (br.ready()) {
//            String[] linha = br.readLine().split(";");
//
//            fila.add(
//                    new Aluno(
//                            new Matricula(linha[0]),
//                            linha[1],
//                            Disciplina.getDisciplinas(linha[2])));
//        }
//
//        br.close();
//        fr.close();
//
//        // Ordenando a fila
//        Queue<Aluno> retorno = new LinkedBlockingQueue<>();
//        while (fila.size() > 0) {
//            Iterator it = fila.iterator();
//            Aluno menor = (Aluno) it.next();
//            while (it.hasNext()) {
//                Aluno aux = (Aluno) it.next();
//                if (comp.compare(menor, aux) > 0) {
//                    menor = aux;
//                }
//            }
//            fila.remove(menor);
//            retorno.add(menor);
//        }
//
//        return retorno.iterator();
//    }
}

package br.com.eduardo.ads4.mmqspp.iterator.persistence;

import br.com.eduardo.ads4.mmqspp.iterator.model.Professor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

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
            case 0:
                estrutura = new PProfessorEstrArrayList(this);
                break;
            case 1:
                estrutura = new PProfessorEstrPilha(this);
                break;
            case 2:
                estrutura = new PProfessorEstrFila(this);
                break;
            case 3:
                estrutura = new PProfessorEstrLista(this);
                break;
            case 4:
                estrutura = new PProfessorEstrArvore(this);
                break;
        }

        return (estrutura != null) ? estrutura.getIterator() : null;
    }
}

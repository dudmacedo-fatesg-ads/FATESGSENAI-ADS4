package br.com.eduardo.ads4.mmqspp.iterator.persistence;

import br.com.eduardo.ads4.mmqspp.iterator.model.Professor;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class PProfessorOrdMatricula extends PProfessor {

    public PProfessorOrdMatricula(File arquivo, int tp_estrutura) {
        super(arquivo, tp_estrutura);
    }

    @Override
    public int comparar(Professor p1, Professor p2) {
        return Integer.compare(p1.getMatricula(), p2.getMatricula());
    }
}

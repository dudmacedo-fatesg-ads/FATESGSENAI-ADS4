package br.com.eduardo.ads4.mmqspp.iterator.persistence;

import br.com.eduardo.ads4.mmqspp.iterator.model.Professor;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class PProfessorOrdDocenciaTitulacaoMatricula extends PProfessor {

    public PProfessorOrdDocenciaTitulacaoMatricula(File arquivo, int tp_estrutura) {
        super(arquivo, tp_estrutura);
    }

    @Override
    public int comparar(Professor p1, Professor p2) {
        if (p1.getExp_docencia() == p2.getExp_docencia()) {
            if (p1.getTitulacao().equals(p2.getTitulacao())) {
                return Integer.compare(p1.getMatricula(), p2.getMatricula());
            } else {
                return p1.getTitulacao().compareTo(p2.getTitulacao());
            }
        } else {
            return Integer.compare(p1.getExp_docencia(), p2.getExp_docencia());
        }
    }
}

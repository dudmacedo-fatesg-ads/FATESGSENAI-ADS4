package br.com.eduardo.ads4.mmqspp.iterator.persistence;

import br.com.eduardo.ads4.mmqspp.iterator.model.Professor;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class PProfessorOrdDepartamentoNome extends PProfessor {

    public PProfessorOrdDepartamentoNome(File arquivo, int tp_estrutura) {
        super(arquivo, tp_estrutura);
    }

    @Override
    public int comparar(Professor p1, Professor p2) {
        if (p1.getDepartamento().equals(p2.getDepartamento())) {
            return p1.getNome().compareTo(p2.getNome());
        }
        else {
            return p1.getDepartamento().compareTo(p2.getDepartamento());
        }
    }
}

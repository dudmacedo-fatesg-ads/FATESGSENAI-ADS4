package br.com.eduardo.templatemethod.persistence;

import br.com.eduardo.templatemethod.model.Professor;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class PProfessorOrdDepartamentoNome extends PProfessor {

    public PProfessorOrdDepartamentoNome(File arquivo) {
        super(arquivo);
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

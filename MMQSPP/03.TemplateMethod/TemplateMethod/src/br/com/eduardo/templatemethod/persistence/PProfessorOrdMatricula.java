package br.com.eduardo.templatemethod.persistence;

import br.com.eduardo.templatemethod.model.Professor;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class PProfessorOrdMatricula extends PProfessor {

    public PProfessorOrdMatricula(File arquivo) {
        super(arquivo);
    }

    @Override
    public int comparar(Professor p1, Professor p2) {
        return Integer.compare(p1.getMatricula(), p2.getMatricula());
    }
}

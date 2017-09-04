package br.com.eduardo.templatemethod.persistence;

import br.com.eduardo.templatemethod.model.Professor;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class PProfessorOrdRegimeNome extends PProfessor {

    public PProfessorOrdRegimeNome(File arquivo) {
        super(arquivo);
    }

    @Override
    public int comparar(Professor p1, Professor p2) {
        if (p1.getRegime().equals(p2.getRegime())) {
            return p1.getNome().compareTo(p2.getNome());
        } else {
            return p1.getRegime().compareTo(p2.getRegime());
        }
    }
}

package br.com.eduardo.templatemethod.persistence;

import br.com.eduardo.templatemethod.model.Professor;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class PProfessorOrdNome extends PProfessor {
    
    public PProfessorOrdNome(File arquivo) {
        super(arquivo);
    }

    @Override
    public int comparar(Professor p1, Professor p2) {
        return p1.getNome().compareToIgnoreCase(p2.getNome());
    }
    
}

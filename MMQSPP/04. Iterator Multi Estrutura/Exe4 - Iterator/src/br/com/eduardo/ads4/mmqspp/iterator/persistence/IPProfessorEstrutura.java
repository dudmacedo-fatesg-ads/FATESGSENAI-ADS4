package br.com.eduardo.ads4.mmqspp.iterator.persistence;

import br.com.eduardo.ads4.mmqspp.iterator.model.Professor;
import java.util.Iterator;

/**
 *
 * @author eduardo
 */
public interface IPProfessorEstrutura extends Runnable {

    public Iterator<Professor> getIterator();
}

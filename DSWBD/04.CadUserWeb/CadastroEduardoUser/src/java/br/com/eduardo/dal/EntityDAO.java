package br.com.eduardo.dal;

import br.com.eduardo.error.DatabaseException;
import java.util.List;

/**
 *
 * @author eduardo
 * @param <T>
 */
public interface EntityDAO<T> {
    public String getTabela();
    public List<T> getAll();
    
    public void create(T obj) throws DatabaseException;
    public T retrieve(Object key) throws DatabaseException;
    public void update(T obj) throws DatabaseException;
    public void delete(T obj) throws DatabaseException;
}

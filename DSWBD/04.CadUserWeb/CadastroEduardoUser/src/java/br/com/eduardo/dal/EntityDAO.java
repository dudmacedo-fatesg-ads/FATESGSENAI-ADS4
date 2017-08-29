package br.com.eduardo.dal;

import br.com.eduardo.error.DatabaseException;

/**
 *
 * @author eduardo
 * @param <T>
 */
public interface EntityDAO<T> {
    public String getTabela();
    
    public void create(T obj) throws DatabaseException;
    public T retrieve(T obj) throws DatabaseException;
    public void update(T obj) throws DatabaseException;
    public void delete(T obj) throws DatabaseException;
}

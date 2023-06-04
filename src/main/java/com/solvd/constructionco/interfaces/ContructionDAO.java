package com.solvd.constructionco.interfaces;


import java.util.List;

/*Provides a generic DAO*/
public interface ContructionDAO<T, ID> {

    public T getById(ID id);
    public void save(T entity);
    public void update(T entity);
    public void delete(ID id);
    public List<T> getAll();
}

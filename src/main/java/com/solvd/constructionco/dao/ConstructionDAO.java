package com.solvd.constructionco.dao;


import java.util.List;

/*Provides a generic DAO*/
public interface ConstructionDAO<T, ID> {

    public T getById(ID id);
    public void save(T entity);
    public void update(T entity);
    public void delete(ID id);
    public List<T> getAll();
}

package com.solvd.constructionco.interfaces;


/*Provides a generic DAO*/
public interface ContructionDAO<T, ID> {

    T getById(ID id);
    void save(T entity);
    void update(T entity);
    void delete(ID id);
}

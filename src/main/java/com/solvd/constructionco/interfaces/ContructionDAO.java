package com.solvd.constructionco.interfaces;


import java.util.List;

/*Provides a generic DAO*/
public interface ContructionDAO<T, ID> {

    T getById(ID id);
    void save(T entity);
    void update(T entity);
    void delete(ID id);
    List<T> getAll();
}

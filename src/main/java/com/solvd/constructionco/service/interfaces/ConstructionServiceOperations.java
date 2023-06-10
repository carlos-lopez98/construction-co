package com.solvd.constructionco.service.interfaces;

import java.util.List;

public interface ConstructionServiceOperations<T, ID> {

    public <T> T getById(ID id);
    public void save(T entity);
    public void update(T entity);
    public void delete(ID id);
    public List<T> getAll();
}

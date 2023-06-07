package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Supplier;

import java.util.List;

public interface iSupplierDAO<Supplier, Integer> extends ConstructionDAO<Supplier, Integer>{

    @Override
    Supplier getById(Integer supplierID);

    @Override
    void save(Supplier supplier);

    @Override
    void update(Supplier supplier);

    @Override
    void delete(Integer supplierID);

    @Override
    List<Supplier> getAll();
}

package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Supplier;

import java.util.List;

public interface ISupplierService extends ConstructionServiceOperations<Supplier, Integer>{

    @Override
    <T> T getById(Integer supplierID);

    @Override
    void save(Supplier supplier);

    @Override
    void update(Supplier supplier);

    @Override
    void delete(Integer supplierID);

    @Override
    List<Supplier> getAll();
}

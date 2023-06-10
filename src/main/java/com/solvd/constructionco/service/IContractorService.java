package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Contractor;

import java.util.List;

public interface IContractorService extends ConstructionServiceOperations<Contractor, Integer>{

    @Override
    <T> T getById(Integer contractorID);

    @Override
    void save(Contractor contractor);

    @Override
    void update(Contractor contractor);

    @Override
    void delete(Integer contractorID);

    @Override
    List<Contractor> getAll();
}

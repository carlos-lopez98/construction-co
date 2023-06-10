package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Contractor;

import java.util.List;

public interface IContractorDAO<Contractor, Integer> extends ConstructionDAO<Contractor, Integer>{

    @Override
    Contractor getById(Integer contractorID);

    @Override
    void save(Contractor contractor);

    @Override
    void update(Contractor contractor);

    @Override
    void delete(Integer contractorID);

    @Override
    List<Contractor> getAll();
}

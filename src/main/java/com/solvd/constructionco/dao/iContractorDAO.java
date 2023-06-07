package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Contractor;

import java.util.List;

public interface iContractorDAO<Contractor, Integer> extends ConstructionDAO<Contractor, Integer>{

    @Override
    Contractor getById(Integer integer);

    @Override
    void save(Contractor contractor);

    @Override
    void update(Contractor contractor);

    @Override
    void delete(Integer integer);

    @Override
    List<Contractor> getAll();
}

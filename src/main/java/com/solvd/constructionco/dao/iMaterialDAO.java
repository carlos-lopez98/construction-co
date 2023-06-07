package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Material;

import java.util.List;

public interface iMaterialDAO<Material, Integer> extends ConstructionDAO<Material, Integer>{

    @Override
    Material getById(Integer integer);

    @Override
    void save(Material entity);

    @Override
    void update(Material entity);

    @Override
    void delete(Integer integer);

    @Override
    List<Material> getAll();
}

package com.solvd.constructionco.service.interfaces;

import com.solvd.constructionco.models.Material;

import java.util.List;

public interface IMaterialService extends ConstructionServiceOperations<Material, Integer>{

    @Override
    <T> T getById(Integer materialID);

    @Override
    void save(Material material);

    @Override
    void update(Material material);

    @Override
    void delete(Integer materialID);

    @Override
    List<Material> getAll();
}

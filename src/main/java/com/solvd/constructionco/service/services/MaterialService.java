package com.solvd.constructionco.service.services;

import com.solvd.constructionco.dao.MaterialDAO;
import com.solvd.constructionco.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.Material;

import java.util.List;

public class MaterialService implements ConstructionServiceOperations<Material, Integer> {

    private MaterialDAO materialDAO;

    public MaterialService(MaterialDAO materialDAO){
        this.materialDAO = materialDAO;
    }

    @Override
    public Material getById(Integer materialID) {
        return materialDAO.getById(materialID);
    }

    @Override
    public void save(Material material) {
        materialDAO.save(material);
    }

    @Override
    public void update(Material material) {
            materialDAO.update(material);
    }

    @Override
    public void delete(Integer materialID) {
        materialDAO.delete(materialID);
    }

    @Override
    public List getAll() {
        return materialDAO.getAll();
    }
}

package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.MaterialDAO;
import com.solvd.constructionco.service.ConstructionServiceOperations;
import com.solvd.constructionco.models.Material;
import com.solvd.constructionco.service.IMaterialService;

import java.util.List;

public class MaterialService implements IMaterialService {

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

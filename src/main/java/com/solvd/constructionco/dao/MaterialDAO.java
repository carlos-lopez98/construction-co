package com.solvd.constructionco.dao;

import com.solvd.constructionco.interfaces.ConstructionDAO;
import com.solvd.constructionco.models.Material;

import java.util.ArrayList;
import java.util.List;

public class MaterialDAO implements ConstructionDAO<Material, Integer> {

    private List<Material> materials;

    public MaterialDAO() {
        materials = new ArrayList<>();
    }

    @Override
    public Material getById(Integer id) {
        for (Material material : materials) {
            if (material.getMaterialId() == id) {
                return material;
            }
        }
        return null;
    }

    @Override
    public void save(Material material) {
        materials.add(material);
    }

    @Override
    public void update(Material material) {
        Material existingMaterial = getById(material.getMaterialId());
        if (existingMaterial != null) {
            existingMaterial.setSupplierId(material.getSupplierId());
            existingMaterial.setMaterialName(material.getMaterialName());
            existingMaterial.setDescription(material.getDescription());
            existingMaterial.setPricePerUnit(material.getPricePerUnit());
            existingMaterial.setUnit(material.getUnit());
        }
    }

    @Override
    public void delete(Integer id) {
        Material materialToRemove = getById(id);
        if (materialToRemove != null) {
            materials.remove(materialToRemove);
        }
    }

    @Override
    public List<Material> getAll() {
        return materials;
    }
}

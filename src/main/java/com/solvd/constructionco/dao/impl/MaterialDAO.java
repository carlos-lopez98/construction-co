package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.IMaterialDAO;
import com.solvd.constructionco.models.Material;

import java.util.ArrayList;
import java.util.List;

public class MaterialDAO implements IMaterialDAO<Material, Integer> {

    private List<Material> materials;

    private static final String GET_ALL_QUERY = "SELECT material_id, supplier_id, material_name, description, price_per_unit FROM materials";
    private static final String GET_BY_ID_QUERY = "SELECT material_id, supplier_id, material_name, description, price_per_unit FROM materials WHERE material_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO materials VALUES(?,?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM materials WHERE material_id = ?";
    private static final String UPDATE_QUERY = "UPDATE materials SET material_id = ?, supplier_id = ?, material_name = ?, description = ?, price_per_unit = ? WHERE material_id = ?";


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

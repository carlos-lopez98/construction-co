package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.ConstructionDAO;
import com.solvd.constructionco.dao.iEquipmentDAO;
import com.solvd.constructionco.models.Equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO implements iEquipmentDAO<Equipment, Integer> {

    private List<Equipment> equipmentList;

    private final String getAllQuery = "SELECT equipment_id, equipment_name, description, status FROM equipment";
    private final String getByIdQuery = "SELECT equipment_id, equipment_name, description, status FROM equipment WHERE equipment_id = ?";
    private final String saveQuery = "INSERT INTO equipment VALUES(?,?,?,?)";
    private final String deleteQuery = "DELETE FROM equipment WHERE equipment_id = ?";
    private final String updateQuery = "UPDATE equipment SET equipment_name, description, status WHERE equipment_id = ?";

    public EquipmentDAO() {
        equipmentList = new ArrayList<>();
    }

    @Override
    public Equipment getById(Integer id) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getEquipmentId() == id) {
                return equipment;
            }
        }
        return null;
    }

    @Override
    public void save(Equipment equipment) {
        equipmentList.add(equipment);
    }

    @Override
    public void update(Equipment equipment) {
        Equipment existingEquipment = getById(equipment.getEquipmentId());
        if (existingEquipment != null) {
            existingEquipment.setEquipmentName(equipment.getEquipmentName());
            existingEquipment.setDescription(equipment.getDescription());
            existingEquipment.setAvailable(equipment.isAvailable());
        }
    }

    @Override
    public void delete(Integer id) {
        Equipment equipmentToRemove = getById(id);
        if (equipmentToRemove != null) {
            equipmentList.remove(equipmentToRemove);
        }
    }

    @Override
    public List<Equipment> getAll() {
        return equipmentList;
    }
}








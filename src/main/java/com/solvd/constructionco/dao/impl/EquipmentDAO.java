package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.IEquipmentDAO;
import com.solvd.constructionco.models.Equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO implements IEquipmentDAO<Equipment, Integer> {

    private List<Equipment> equipmentList;

    private static final String GET_ALL_QUERY = "SELECT equipment_id, equipment_name, description, status FROM equipment";
    private static final String GET_BY_ID_QUERY = "SELECT equipment_id, equipment_name, description, status FROM equipment WHERE equipment_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO equipment VALUES(?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM equipment WHERE equipment_id = ?";
    private static final String UPDATE_QUERY = "UPDATE equipment SET equipment_name = ?, description = ?, status = ? WHERE equipment_id = ?";

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








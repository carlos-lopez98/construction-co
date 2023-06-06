package com.solvd.constructionco.dao;

import com.solvd.constructionco.interfaces.ConstructionDAO;
import com.solvd.constructionco.models.Equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO implements ConstructionDAO<Equipment, Integer> {

    private List<Equipment> equipmentList;

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








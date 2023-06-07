package com.solvd.constructionco.service.services;

import com.solvd.constructionco.dao.impl.EquipmentDAO;
import com.solvd.constructionco.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.Equipment;

import java.util.List;

public class EquipmentService implements ConstructionServiceOperations<Equipment, Integer> {

    private EquipmentDAO equipmentDAO;

    public EquipmentService(EquipmentDAO equipmentDAO){
        this.equipmentDAO = equipmentDAO;
    }

    @Override
    public Equipment getById(Integer equipmentID) {
        return equipmentDAO.getById(equipmentID);
    }

    @Override
    public void save(Equipment equipment) {
            equipmentDAO.save(equipment);
    }

    @Override
    public void update(Equipment equipment) {
            equipmentDAO.update(equipment);
    }

    @Override
    public void delete(Integer equipmentID) {
        equipmentDAO.delete(equipmentID);
    }

    @Override
    public List getAll() {
        return equipmentDAO.getAll();
    }
}

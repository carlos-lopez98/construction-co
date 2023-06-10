package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Equipment;

import java.util.List;

public interface IEquipmentService extends ConstructionServiceOperations<Equipment, Integer>{

    @Override
    <T> T getById(Integer equipmentID);

    @Override
    void save(Equipment equipment);

    @Override
    void update(Equipment equipment);

    @Override
    void delete(Integer equipmentID);

    @Override
    List<Equipment> getAll();
}

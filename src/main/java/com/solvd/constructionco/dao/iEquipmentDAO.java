package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Equipment;

import java.util.List;

public interface iEquipmentDAO<Equipment, Integer> extends ConstructionDAO<Equipment, Integer>{


    @Override
    Equipment getById(Integer equipmentID);

    @Override
    void save(Equipment equipment);

    @Override
    void update(Equipment equipment);

    @Override
    void delete(Integer equipmentID);

    @Override
    List<Equipment> getAll();
}

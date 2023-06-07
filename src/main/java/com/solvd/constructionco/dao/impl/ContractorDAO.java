package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.ConstructionDAO;
import com.solvd.constructionco.models.Contractor;

import java.util.ArrayList;
import java.util.List;

public class ContractorDAO implements ConstructionDAO<Contractor, Integer> {


    private List<Contractor> contractors;

    public ContractorDAO() {
        contractors = new ArrayList<>();
    }

    @Override
    public Contractor getById(Integer id) {
        for (Contractor contractor : contractors) {
            if (contractor.getContractorId() == id) {
                return contractor;
            }
        }
        return null;
    }

    @Override
    public void save(Contractor contractor) {
        contractors.add(contractor);
    }

    @Override
    public void update(Contractor contractor) {
        Contractor existingContractor = getById(contractor.getContractorId());
        if (existingContractor != null) {
            existingContractor.setContractorName(contractor.getContractorName());
            existingContractor.setEmail(contractor.getEmail());
            existingContractor.setPhoneNumber(contractor.getPhoneNumber());
            existingContractor.setAddress(contractor.getAddress());
        }
    }

    @Override
    public void delete(Integer id) {
        Contractor contractorToRemove = getById(id);
        if (contractorToRemove != null) {
            contractors.remove(contractorToRemove);
        }
    }

    @Override
    public List<Contractor> getAll() {
        return contractors;
    }
}









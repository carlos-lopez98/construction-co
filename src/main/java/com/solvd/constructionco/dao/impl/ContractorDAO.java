package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.iContractorDAO;
import com.solvd.constructionco.models.Contractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ContractorDAO implements iContractorDAO<Contractor, Integer> {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private List<Contractor> contractors;


    private final String getAllQuery = "SELECT contractor_id, contractor_name, email, phone_number, address FROM contractors";
    private final String getByIdQuery = "SELECT contractor_id, contractor_name, email, phone_number, address FROM contractors WHERE contractor_id = ?";
    private final String saveQuery = "INSERT INTO contractors VALUES(?,?,?,?,?)";
    private final String deleteQuery = "DELETE FROM contractors WHERE contractor_id = ?";
    private final String updateQuery = "UPDATE contractors SET contractor_name = ?, email = ?, phone_number = ?, address = ? WHERE contractor_id = ?";


    public ContractorDAO() {
        contractors = new ArrayList<>();
    }

    @Override
    public Contractor getById(Integer contractorID) {
        for (Contractor contractor : contractors) {
            if (contractor.getContractorId() == contractorID) {
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
    public void delete(Integer contractorID) {
        Contractor contractorToRemove = getById(contractorID);
        if (contractorToRemove != null) {
            contractors.remove(contractorToRemove);
        }
    }

    @Override
    public List<Contractor> getAll() {
        return contractors;
    }
}









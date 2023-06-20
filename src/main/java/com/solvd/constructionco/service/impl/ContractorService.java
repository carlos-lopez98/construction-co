package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.ContractorDAO;
import com.solvd.constructionco.exceptions.NoContractorInDatabaseException;
import com.solvd.constructionco.models.Contractor;
import com.solvd.constructionco.service.interfaces.IContractorService;

import java.util.List;

//Do the validation/ input data null checks in here

public class ContractorService implements IContractorService {

    private static final ContractorDAO contractorDAO = new ContractorDAO();

    public ContractorService() {
    }

    @Override
    public Contractor getById(Integer contractorId) {


        if(contractorId == null || contractorId < 0){
            throw new IllegalArgumentException("Id entered is incorrect");
        }

        Contractor contractor = contractorDAO.getById(contractorId);

        if(contractor == null){
            throw new NoContractorInDatabaseException("Please add this contractor to database, currently this contractor is not in database");
        }

        return contractor;
    }

    @Override
    public void save(Contractor entity) {
        contractorDAO.save(entity);
    }

    @Override
    public void update(Contractor entity) {
        contractorDAO.update(entity);
    }

    @Override
    public void delete(Integer id) {
        contractorDAO.delete(id);
    }

    @Override
    public List<Contractor> getAll() {
        return contractorDAO.getAll();
    }
}

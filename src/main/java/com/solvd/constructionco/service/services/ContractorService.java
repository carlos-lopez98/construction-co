package com.solvd.constructionco.service.services;

import com.solvd.constructionco.dao.ContractorDAO;
import com.solvd.constructionco.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.Contractor;

import java.util.List;

public class ContractorService implements ConstructionServiceOperations<Contractor, Integer> {

    private ContractorDAO contractorDAO;

    public ContractorService(ContractorDAO contractorDAO) {
        this.contractorDAO = contractorDAO;
    }

    @Override
    public Contractor getById(Integer id) {
        return contractorDAO.getById(id);
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

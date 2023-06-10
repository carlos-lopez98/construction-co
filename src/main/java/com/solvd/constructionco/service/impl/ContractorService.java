package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.ContractorDAO;
import com.solvd.constructionco.models.Contractor;
import com.solvd.constructionco.service.interfaces.IContractorService;

import java.util.List;

//Do the validation/ input data null checks in here

public class ContractorService implements IContractorService {

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

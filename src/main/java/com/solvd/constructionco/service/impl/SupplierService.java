package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.SupplierDAO;
import com.solvd.constructionco.models.Supplier;
import com.solvd.constructionco.service.interfaces.ISupplierService;

import java.util.List;

public class SupplierService implements ISupplierService {

    private SupplierDAO supplierDAO;

    public SupplierService(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    @Override
    public Supplier getById(Integer supplierID) {
        return supplierDAO.getById(supplierID);
    }

    @Override
    public void save(Supplier supplier) {
        supplierDAO.save(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        supplierDAO.update(supplier);
    }

    @Override
    public void delete(Integer supplierID) {
        supplierDAO.delete(supplierID);
    }

    @Override
    public List getAll() {
        return supplierDAO.getAll();
    }
}

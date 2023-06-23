package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.SupplierDAO;
import com.solvd.constructionco.models.Supplier;
import com.solvd.constructionco.service.interfaces.ISupplierService;

import java.util.List;

public class SupplierService implements ISupplierService {

    private static final SupplierDAO supplierDAO = new SupplierDAO();

    public SupplierService( ) {

    }

    @Override
    public Supplier getById(Integer supplierID) {

        if(supplierID == null || supplierID < 0){

            throw new IllegalArgumentException("SupplierId is not in correct formate");
        }

        Supplier supplier = supplierDAO.getById(supplierID);

        if(supplier == null){
            throw new RuntimeException("Supplier is not inside of database");
        }

        return supplier;
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
